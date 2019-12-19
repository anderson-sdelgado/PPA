package br.com.usinasantafe.ppa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BTPesagemActivity extends ActivityGeneric {

    private PPAContext ppaContext;
    private TextView textViewPesagemBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_pesagem);

        textViewPesagemBT = (TextView) findViewById(R.id.textViewPesagemBT);
        Button buttonPesagemBT = (Button) findViewById(R.id.buttonPesagemBT);
        Button buttonPesagemDig = (Button) findViewById(R.id.buttonPesagemDig);
        Button buttonRetCapturaPesagem = (Button) findViewById(R.id.buttonRetCapturaPesagem);

        buttonPesagemDig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BTPesagemActivity.this, DigPesagemActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonRetCapturaPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BTPesagemActivity.this, ListaOSActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
