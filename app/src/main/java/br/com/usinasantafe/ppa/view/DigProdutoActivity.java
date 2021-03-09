package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class DigProdutoActivity extends ActivityGeneric {

    private EditText editTextProd;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_produto);

        editTextProd = (EditText)  findViewById(R.id.editTextPadrao);
        Button buttonOkOS =  (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS =  (Button) findViewById(R.id.buttonCancPadrao);

        ppaContext = (PPAContext) getApplication();

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                if (!editTextProd.getText().toString().equals("")) {

                    ppaContext.getPesagemCTR().getItemPesagemBean().setProdItemPesagem(editTextProd.getText().toString().trim());

                    Intent it = new Intent( DigProdutoActivity.this, DigOSActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DigProdutoActivity.this, MenuCaptPesagemActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}