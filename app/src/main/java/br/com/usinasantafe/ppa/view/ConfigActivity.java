package br.com.usinasantafe.ppa.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.util.ConexaoWeb;
import br.com.usinasantafe.ppa.util.AtualDadosServ;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText equipEditText;
    private EditText senhaEditText;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ppaContext = (PPAContext) getApplication();

        Button buttonOkConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button buttonCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button buttonAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        equipEditText = (EditText)  findViewById(R.id.editTextEquip);
        senhaEditText = (EditText)  findViewById(R.id.editTextSenha);

        if(ppaContext.getConfigCTR().hasElements()){

            ConfigBean configBean = ppaContext.getConfigCTR().getConfig();
            equipEditText.setText(String.valueOf(configBean.getIdEquipConfig()));
            senhaEditText.setText(configBean.getSenhaConfig());

        }

        buttonOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!equipEditText.getText().toString().equals("") &&
                        !senhaEditText.getText().toString().equals("")){

                    if(ppaContext.getConfigCTR().verEquip(Long.valueOf(equipEditText.getText().toString()))){

                        ppaContext.getConfigCTR().insConfig(Long.valueOf(equipEditText.getText().toString()), senhaEditText.getText().toString());

                        Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfigActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    AtualDadosServ.getInstance().atualizarBD(progressBar);
                    AtualDadosServ.getInstance().setContext(ConfigActivity.this);

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();

                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
