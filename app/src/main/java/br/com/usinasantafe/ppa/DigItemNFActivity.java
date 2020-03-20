package br.com.usinasantafe.ppa;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DigItemNFActivity extends ActivityGeneric {

    private EditText editTextItemNF;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_item_nf);

        ppaContext = (PPAContext) getApplication();

        editTextItemNF = (EditText)  findViewById(R.id.editTextItemNF);
        Button buttonOkItemNF =  (Button) findViewById(R.id.buttonOkItemNF);
        Button buttonCancItemNF =  (Button) findViewById(R.id.buttonCancItemNF);

        editTextItemNF.addTextChangedListener(new TextWatcher() {

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
                    editTextItemNF.setText(s);
                    editTextItemNF.setSelection(editTextItemNF.length()); //fix reverse texting
                }
            }
        });

        buttonOkItemNF.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                if (!editTextItemNF.getText().toString().equals("")) {

                    String notaFiscal = editTextItemNF.getText().toString().trim();
                    ppaContext.getPesagemCTR().getItemPesagemBean().setNroNFItemPes(Long.parseLong(notaFiscal));

                    Intent it = new Intent(DigItemNFActivity.this, DigOSActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancItemNF.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DigItemNFActivity.this, DigNotaFiscalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
