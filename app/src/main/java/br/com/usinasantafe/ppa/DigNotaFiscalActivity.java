package br.com.usinasantafe.ppa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DigNotaFiscalActivity extends ActivityGeneric {

    private EditText editTextNotaFiscal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_nota_fiscal);

        editTextNotaFiscal = (EditText)  findViewById(R.id.editTextNotaFiscal);
        Button buttonOkNotaFiscal =  (Button) findViewById(R.id.buttonOkNotaFiscal);
        Button buttonCancNotaFiscal =  (Button) findViewById(R.id.buttonCancNotaFiscal);

        buttonOkNotaFiscal.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DigNotaFiscalActivity.this, DigItemNFActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonCancNotaFiscal.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DigNotaFiscalActivity.this, DigPlacaVeicActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
