package br.com.usinasantafe.ppa.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class ComentFalhaActivity extends ActivityGeneric {

    private PPAContext ppaContext;
    private EditText editTextComent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coment_falha);

        ppaContext = (PPAContext) getApplication();

        editTextComent = (EditText) findViewById(R.id.editTextComentario);
        Button buttonRetComent = (Button) findViewById(R.id.buttonRetComent);
        Button buttonAvancaComent = (Button) findViewById(R.id.buttonAvancaComent);

        buttonAvancaComent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextComent.getText().toString().equals("")) {

                    ppaContext.getPesagemCTR().insItemPes(ppaContext.getPesagem(), editTextComent.getText().toString(), getLatitude(), getLatitude());

                    Intent it = new Intent(ComentFalhaActivity.this, MsgPesagemActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( ComentFalhaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! COMENTE O MOTIVO QUE TE LEVOU A DIGITAR O VALOR DA PESAGEM E NÃO CAPTURA O MESMO VIA BLUETOOTH.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();
                }

            }

        });

        buttonRetComent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ComentFalhaActivity.this, DigPesoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
