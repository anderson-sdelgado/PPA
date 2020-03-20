package br.com.usinasantafe.ppa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ppa.util.ConexaoWeb;
import br.com.usinasantafe.ppa.util.VerifDadosServ;

public class DigOSActivity extends ActivityGeneric {

    private EditText editTextOS;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();
    private boolean verDados;
    private PPAContext ppaContext;
    private String nroOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_os);

        editTextOS = (EditText)  findViewById(R.id.editTextOS);
        Button buttonOkOS =  (Button) findViewById(R.id.buttonOkOS);
        Button buttonCancOS =  (Button) findViewById(R.id.buttonCancOS);

        ppaContext = (PPAContext) getApplication();

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                if (!editTextOS.getText().toString().equals("")) {

                    nroOS = editTextOS.getText().toString().trim();

                    if(ppaContext.getPesagemCTR().verOSBD(nroOS)){

                        Long statusCon;
                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(DigOSActivity.this)) {
                            statusCon = 1L;
                        }
                        else{
                            statusCon = 0L;
                        }

                        ppaContext.getPesagemCTR().getItemPesagemBean().setNroOSItemPes(nroOS);
                        ppaContext.getPesagemCTR().getItemPesagemBean().setStatusConOSItemPes(statusCon);

                        Intent it = new Intent(DigOSActivity.this, MenuPesagemActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{
                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(DigOSActivity.this)) {

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            verDados = true;

                            ppaContext.getPesagemCTR().verOSServ(nroOS, DigOSActivity.this);

                        }
                        else{

                            ppaContext.getPesagemCTR().getItemPesagemBean().setNroOSItemPes(nroOS);
                            ppaContext.getPesagemCTR().getItemPesagemBean().setStatusConOSItemPes(0L);

                            Intent it = new Intent(DigOSActivity.this, MenuPesagemActivity.class);
                            startActivity(it);
                            finish();

                        }
                    }

                }

            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                if(ppaContext.getPesagemCTR().verStatusConPlacaVeic()){
                    Intent it = new Intent(DigOSActivity.this, ListaItemNFActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(DigOSActivity.this, DigItemNFActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }


    public void onBackPressed()  {
    }

    public void avancaSucesso(){
        verDados = false;
        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }
        ppaContext.getPesagemCTR().getItemPesagemBean().setNroOSItemPes(nroOS);
        ppaContext.getPesagemCTR().getItemPesagemBean().setStatusConOSItemPes(1L);
        Intent it = new Intent(DigOSActivity.this, MenuPesagemActivity.class);
        startActivity(it);
        finish();
    }

    public void msg(String texto){
        verDados = false;
        this.progressBar.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(DigOSActivity.this);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        alerta.show();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(verDados) {

                verDados = false;

                VerifDadosServ.getInstance().cancelVer();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                ppaContext.getPesagemCTR().getItemPesagemBean().setNroOSItemPes(nroOS);
                ppaContext.getPesagemCTR().getItemPesagemBean().setStatusConOSItemPes(0L);
                Intent it = new Intent(DigOSActivity.this, MenuPesagemActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

    public void setVerDados(boolean verDados) {
        this.verDados = verDados;
    }
}
