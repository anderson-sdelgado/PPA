package br.com.usinasantafe.ppa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DigNotaFiscalActivity extends ActivityGeneric {

    private EditText editTextNotaFiscal;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_nota_fiscal);

        ppaContext = (PPAContext) getApplication();

        editTextNotaFiscal = (EditText)  findViewById(R.id.editTextNotaFiscal);
        Button buttonOkNotaFiscal =  (Button) findViewById(R.id.buttonOkNotaFiscal);
        Button buttonCancNotaFiscal =  (Button) findViewById(R.id.buttonCancNotaFiscal);

        buttonOkNotaFiscal.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                if (!editTextNotaFiscal.getText().toString().equals("")) {

                    String notaFiscal = editTextNotaFiscal.getText().toString().trim();
                    ppaContext.getPesagemCTR().getItemPesagemBean().setNroNFItemPes(Long.parseLong(notaFiscal));

                    Intent it = new Intent(DigNotaFiscalActivity.this, DigItemNFActivity.class);
                    startActivity(it);
                    finish();
                    
                }

            }
        });

        buttonCancNotaFiscal.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(DigNotaFiscalActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE RETORNAR, O RETORNO CAUSARAM A PERDA DE TODAS AS INFORMAÇÕES DE PESAGEM?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ppaContext.getPesagemCTR().deleteCabecAberto();

                        Intent it = new Intent(DigNotaFiscalActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();
                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }
        });

    }

    public void onBackPressed()  {
    }

}
