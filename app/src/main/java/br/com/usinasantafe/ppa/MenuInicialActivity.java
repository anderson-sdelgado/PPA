package br.com.usinasantafe.ppa;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MenuInicialActivity extends Activity {

    private ListView menuListView;
    private PPAContext ppaContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTA PESAGEM");
        itens.add("ATUALIZAR DADOS");
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

                if (text.equals("APONTA PESAGEM")) {

                        Intent it = new Intent(MenuInicialActivity.this, ListaVeiculoActivity.class);
                        startActivity(it);
                        finish();

//                    ColabBean colabBean = new ColabBean();
//
//                    if (colabBean.hasElements()) {
//
//                        pstContext.getAbordagemCTR().clearBD();
//
//                        Intent it = new Intent(MenuInicialActivity.this, ObservadorDigActivity.class);
//                        startActivity(it);
//                        finish();
//                    }
//                    else{
//
//                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
//                        alerta.setTitle("ATENÇÃO");
//                        alerta.setMessage("BASE DE DADOS DESATUALIZADA! POR FAVOR, SELECIONE A OPÇÃO 'ATUALIZAR DADOS' PARA ATUALIZAR A BASE DE DADOS ANTES DE CRIAR UM NOVO FORMULÁRIO.");
//                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        alerta.show();
//
//                    }

                } else if (text.equals("ATUALIZAR DADOS")) {

//                    ConexaoWeb conexaoWeb = new ConexaoWeb();
//
//                    if(conexaoWeb.verificaConexao(MenuInicialActivity.this)){
//
//                        progressBar = new ProgressDialog(v.getContext());
//                        progressBar.setCancelable(true);
//                        progressBar.setMessage("ATUALIZANDO BASE DE DADOS...");
//                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                        progressBar.setProgress(0);
//                        progressBar.setMax(100);
//                        progressBar.show();
//
//                        AtualDadosServ.getInstance().atualizarBD(progressBar);
//                        AtualDadosServ.getInstance().setContext(MenuInicialActivity.this);
//
//                    }
//                    else{
//
//                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
//                        alerta.setTitle("ATENÇÃO");
//                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
//                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        alerta.show();
//
//                    }

                } else if (text.equals("SAIR")) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }

        });

    }

    public void startTimer() {

        Intent intent = new Intent(this, AlarmClass.class);
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null);

        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }

        if (alarmeAtivo) {

            Log.i("PST", "NOVO TIMER");
            PendingIntent p = PendingIntent.getBroadcast(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        } else {
            Log.i("PMM", "TIMER já ativo");
        }
    }

}
