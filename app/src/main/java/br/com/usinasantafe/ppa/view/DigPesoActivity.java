package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class DigPesoActivity extends ActivityGeneric {

    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_peso);

        ppaContext = (PPAContext) getApplication();

        Button buttonOkDigPesagem = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancDigPesagem = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkDigPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String pesagemString = editTextPadrao.getText().toString();
                    Double pesagem = Double.valueOf(pesagemString.replace(",", "."));

                    ppaContext.getPesagemCTR().getItemPesagemBean().setPesoItemPes(pesagem);

                    Intent it = new Intent(DigPesoActivity.this, ComentFalhaActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonCancDigPesagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
            Intent it = new Intent(DigPesoActivity.this, MenuCaptPesagemActivity.class);
            startActivity(it);
            finish();
    }

}
