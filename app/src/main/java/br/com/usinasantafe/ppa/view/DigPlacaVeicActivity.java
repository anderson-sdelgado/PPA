package br.com.usinasantafe.ppa.view;

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

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.util.ConexaoWeb;
import br.com.usinasantafe.ppa.util.VerifDadosServ;

public class DigPlacaVeicActivity extends ActivityGeneric {

    private EditText editTextPlaca;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();
    private PPAContext ppaContext;
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
                if(!s.equals(s.toUpperCase())) {
                    s = s.toUpperCase();
                    editTextPlaca.setText(s);
                    editTextPlaca.setSelection(editTextPlaca.length());
                }
            }
        });

        buttonOkPlaca.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                if (!editTextPlaca.getText().toString().equals("")) {

                    placa = editTextPlaca.getText().toString().trim();

                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    if (ppaContext.getPesagemCTR().verOrdCarreg(placa)) {

                        ppaContext.getPesagemCTR().criarCabecPes(placa, 1L);

                        Intent it = new Intent(DigPlacaVeicActivity.this, ListaEquipPesagActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        if (conexaoWeb.verificaConexao(DigPlacaVeicActivity.this)) {

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO PLACA...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            VerifDadosServ.getInstance().setVerTerm(false);
                            ppaContext.getPesagemCTR().verPlacaVeicServ(placa, DigPlacaVeicActivity.this, ListaEquipPesagActivity.class, progressBar);

                        } else {

                            ppaContext.getPesagemCTR().criarCabecPes(placa, 0L);
                            VerifDadosServ.getInstance().setVerTerm(true);
                            msg("FALHA NA CONEXÃO! POR FAVOR, PARA INSERIR A PESAGEM NESSE CAMINHÃO TERÁ QUE SER INSERIDO OS DADOS MANUALMENTE.");

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

    public void msg(String texto){
        AlertDialog.Builder alerta = new AlertDialog.Builder(DigPlacaVeicActivity.this);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent it = new Intent(DigPlacaVeicActivity.this, ListaEquipPesagActivity.class);
                startActivity(it);
                finish();

            }
        });
        alerta.show();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!VerifDadosServ.getInstance().isVerTerm()) {

                VerifDadosServ.getInstance().cancelVer();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                ppaContext.getPesagemCTR().criarCabecPes(placa, 0L);

                msg("FALHA NA CONEXÃO! POR FAVOR, DIGITE O RESTANTE DAS INFORMAÇÕES PARA DÁ CONTINUIDADE A PESAGEM SEM SINAL.");

            }

        }
    };

}
