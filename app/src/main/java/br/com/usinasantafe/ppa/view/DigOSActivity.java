package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class DigOSActivity extends ActivityGeneric {

    private EditText editTextOS;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_os);

        editTextOS = (EditText)  findViewById(R.id.editTextPadrao);
        Button buttonOkOS =  (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS =  (Button) findViewById(R.id.buttonCancPadrao);

        ppaContext = (PPAContext) getApplication();

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                if (!editTextOS.getText().toString().equals("")) {

                    ppaContext.getPesagemCTR().getItemPesagemBean().setNroOSItemPes(Long.parseLong(editTextOS.getText().toString().trim()));

                    Intent it = new Intent(DigOSActivity.this, DigProdutoActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DigOSActivity.this, ListaEquipPesagActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
