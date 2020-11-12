package br.com.usinasantafe.ppa.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class CameraActivity extends ActivityGeneric {

    private ImageView imagem;
    private boolean capt;
    private Bitmap bitmap;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        capt = false;
        ppaContext = (PPAContext) getApplication();

        imagem = (ImageView) findViewById(R.id.imagem);
        Button buttonCapturaFoto = (Button) findViewById(R.id.buttonCapturaFoto);
        Button buttonAvancaFoto = (Button) findViewById(R.id.buttonAvancaFoto);
        Button buttonRetFoto = (Button) findViewById(R.id.buttonRetFoto);

        buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        buttonAvancaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(capt){
                    ppaContext.getPesagemCTR().fechCabPesagem(bitmap);
                    Intent it = new Intent(CameraActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonRetFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE RETORNAR, O RETORNO CAUSARAM A PERDA DE TODAS AS INFORMAÇÕES DE PESAGEM?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ppaContext.getPesagemCTR().deleteCabecAberto();

                        Intent it = new Intent(CameraActivity.this, MenuInicialActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){
            capt = true;
            bitmap = (Bitmap) data.getExtras().get("data");
            imagem.setImageBitmap(bitmap);
        }

    }

    public void onBackPressed() {
    }

}
