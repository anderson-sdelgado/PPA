package br.com.usinasantafe.ppa.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.usinasantafe.ppa.AlarmClass;
import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.util.ConexaoWeb;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;
import br.com.usinasantafe.ppa.util.Tempo;
import br.com.usinasantafe.ppa.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuListView;
    private PPAContext ppaContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        ppaContext = (PPAContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        progressBar = new ProgressDialog(this);

        if (!checkPermission(Manifest.permission.INTERNET)) {
            String[] PERMISSIONS = {android.Manifest.permission.INTERNET};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.ACCESS_NETWORK_STATE)) {
            String[] PERMISSIONS = {android.Manifest.permission.ACCESS_NETWORK_STATE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

//        if(ppaContext.getPesagemCTR().verCabecPesAberto()){
//            if(ppaContext.getPesagemCTR().getStatusConVeicCabPes() == 1L){
//                Intent it = new Intent(MenuInicialActivity.this, ListaNotaFiscalActivity.class);
//                startActivity(it);
//                finish();
//            }
//            else{
//                Intent it = new Intent(MenuInicialActivity.this, DigNotaFiscalActivity.class);
//                startActivity(it);
//                finish();
//            }
//        }
//        else {
            atualizarAplic();
//        }

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("PESAGEM");
        itens.add("CONFIGURAÇÕES");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("PESAGEM")) {

                    if(ppaContext.getPesagemCTR().hasElementsFunc() && ppaContext.getConfigCTR().hasElements()){

                        Intent it = new Intent(MenuInicialActivity.this, LeitorFuncActivity.class);
                        startActivity(it);
                        finish();

                    }

                } else if (text.equals("CONFIGURAÇÕES")) {

                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("SAIR")) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }

        });

    }

    public void atualizarAplic(){
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            if (ppaContext.getConfigCTR().hasElements()) {
                progressBar.setCancelable(true);
                progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                progressBar.show();
                VerifDadosServ.getInstance().verAtualAplic(ppaContext.versaoAplic, this, progressBar);
            }
        } else {
            startTimer();
        }
    }

    public void startTimer() {

        Intent intent = new Intent(this, AlarmClass.class);
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null);

        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }

        if (alarmeAtivo) {

            Log.i("PPA", "NOVO TIMER");
            PendingIntent p = PendingIntent.getBroadcast(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        } else {
            Log.i("PPA", "TIMER já ativo");
        }
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            int status = EnvioDadosServ.getInstance().getStatusEnvio();
            if (status == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            }
            else if (status == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            }
            else if (status == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

}
