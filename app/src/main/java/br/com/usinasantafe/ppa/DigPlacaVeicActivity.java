package br.com.usinasantafe.ppa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ppa.util.ConexaoWeb;
import br.com.usinasantafe.ppa.util.VerifDadosServ;

public class DigPlacaVeicActivity extends ActivityGeneric{

    private EditText editTextPlaca;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();
    private PPAContext ppaContext;
    private boolean verDados;
    private String placa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_placa_veic);

        editTextPlaca = (EditText)  findViewById(R.id.editTextPlaca);
        Button buttonOkPlaca =  (Button) findViewById(R.id.buttonOkPlaca);
        Button buttonCancPlaca =  (Button) findViewById(R.id.buttonCancPlaca);

        ppaContext = (PPAContext) getApplication();

        editTextPlaca.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable et) {
                String s = et.toString();
                if(!s.equals(s.toUpperCase()))
                {
                    s = s.toUpperCase();
                    editTextPlaca.setText(s);
                    editTextPlaca.setSelection(editTextPlaca.length()); //fix reverse texting
                }
            }
        });

        buttonOkPlaca.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                if (!editTextPlaca.getText().toString().equals("")) {

                    placa = editTextPlaca.getText().toString().trim();

                    if(ppaContext.getPesagemCTR().verPlacaVeicBD(placa)){

                        Long statusCon;
                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(DigPlacaVeicActivity.this)) {
                            statusCon = 1L;
                        }
                        else{
                            statusCon = 0L;
                        }

                        ppaContext.getPesagemCTR().criarCabecPes(placa, statusCon);

                        Intent it = new Intent(DigPlacaVeicActivity.this, ListaNotaFiscalActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{
                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(DigPlacaVeicActivity.this)) {

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            verDados = true;

                            ppaContext.getPesagemCTR().verPlacaVeicServ(placa, DigPlacaVeicActivity.this);

                        }
                        else{

                            ppaContext.getPesagemCTR().criarCabecPes(placa, 0L);

                            msg("FALHA NA CONEXÃO! POR FAVOR, DIGITE O RESTANTE DAS INFORMAÇÕES PARA DÁ CONTINUIDADE A PESAGEM SEM SINAL.");

                            Intent it = new Intent(DigPlacaVeicActivity.this, DigNotaFiscalActivity.class);
                            startActivity(it);
                            finish();

                        }
                    }

                }

            }
        });

        buttonCancPlaca.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DigPlacaVeicActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
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
        ppaContext.getPesagemCTR().criarCabecPes(placa, 1L);
        Intent it = new Intent(DigPlacaVeicActivity.this, ListaNotaFiscalActivity.class);
        startActivity(it);
        finish();
    }

    public void msg(String texto){
        verDados = false;
        this.progressBar.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(DigPlacaVeicActivity.this);
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

                msg("FALHA NA CONEXÃO! POR FAVOR, DIGITE O RESTANTE DAS INFORMAÇÕES PARA DÁ CONTINUIDADE A PESAGEM SEM SINAL.");

                ppaContext.getPesagemCTR().criarCabecPes(placa, 0L);

                Intent it = new Intent(DigPlacaVeicActivity.this, DigNotaFiscalActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

    public void setVerDados(boolean verDados) {
        this.verDados = verDados;
    }
}
