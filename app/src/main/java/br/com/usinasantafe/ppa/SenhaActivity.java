package br.com.usinasantafe.ppa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        ppaContext = (PPAContext) getApplication();

        editTextSenha = (EditText)  findViewById(R.id.editTextSenha);
        Button buttonOkSenha =  (Button) findViewById(R.id.buttonOkSenha);
        Button buttonCancSenha = (Button) findViewById(R.id.buttonCancSenha);

        buttonOkSenha.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                ConfigBean configBean = new ConfigBean();

                if (!ppaContext.getConfigCTR().hasElements()) {

                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    if (ppaContext.getConfigCTR().getConfigSenha(editTextSenha.getText().toString())) {
                        Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                        startActivity(it);
                        finish();
                    }

                }

            }
        });

        buttonCancSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
