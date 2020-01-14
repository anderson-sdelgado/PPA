package br.com.usinasantafe.ppa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MsgPesagemActivity extends Activity {

    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_pesagem);

        ppaContext = (PPAContext) getApplication();

        Button buttonSimMsgPesagem = (Button) findViewById(R.id.buttonSimMsgPesagem);
        Button buttonNaoMsgPesagem = (Button) findViewById(R.id.buttonNaoMsgPesagem);

        buttonSimMsgPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ppaContext.getPesagemCTR().verStatusConPlacaVeic()){
                    Intent it = new Intent(MsgPesagemActivity.this, ListaNotaFiscalActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(MsgPesagemActivity.this, DigPlacaVeicActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonNaoMsgPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgPesagemActivity.this, CameraActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed() {
    }

}
