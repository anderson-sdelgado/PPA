package br.com.usinasantafe.ppa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.util.ConexaoWeb;
import br.com.usinasantafe.ppa.util.UpdateBD;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextMatricFunc;
    private EditText editTextSenha;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ppaContext = (PPAContext) getApplication();

        Button btOkConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button btCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button btAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        editTextMatricFunc = (EditText)  findViewById(R.id.editTextMatricFunc);
        editTextSenha = (EditText)  findViewById(R.id.editTextSenha);

        if(ppaContext.getConfigCTR().hasElements()){

            ConfigBean configBean = ppaContext.getConfigCTR().getConfig();
            editTextMatricFunc.setText(String.valueOf(configBean.getMatricFuncConfig()));
            editTextSenha.setText(configBean.getSenhaConfig());

        }


        btOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextMatricFunc.getText().toString().equals("") &&
                        !editTextSenha.getText().toString().equals("")){

                    if(ppaContext.getConfigCTR().verFunc(Long.valueOf(editTextMatricFunc.getText().toString()))){

                        ppaContext.getConfigCTR().insConfig(Long.valueOf(editTextMatricFunc.getText().toString()), editTextSenha.getText().toString());

                        Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        btCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        btAtualBDConfig.setOnClickListener(new View.OnClickListener() {
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
                    UpdateBD.getInstance().atualizarBD(progressBar);
                    UpdateBD.getInstance().setContext(ConfigActivity.this);
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
