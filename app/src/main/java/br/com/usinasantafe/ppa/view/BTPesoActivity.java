package br.com.usinasantafe.ppa.view;

import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class BTPesoActivity extends ActivityGeneric {

    private static final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private InputStream in;
    private TextView textViewPeso;
    private TextView textViewStatus;
    private Button buttonCapturaPeso;
    private Button buttonAvancaBTPesagem;
    private Button buttonRetBTPesagem;
    private boolean capturaPeso;
    private PPAContext ppaContext;
    private Double peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_peso);

        ppaContext = (PPAContext) getApplication();

        buttonCapturaPeso = (Button) findViewById(R.id.buttonCapturaPeso);
        buttonAvancaBTPesagem = (Button) findViewById(R.id.buttonAvancaBTPesagem);
        buttonRetBTPesagem = (Button) findViewById(R.id.buttonRetBTPesagem);
        textViewPeso = (TextView) findViewById(R.id.textViewPeso);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);

        textViewStatus.setText("CONECTANDO A BALANÇA...");
        textViewStatus.setTextColor(Color.BLACK);

        device = getIntent().getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        TextView textViewTitBTPesagem = (TextView) findViewById(R.id.textViewTitBTPesagem);
        textViewTitBTPesagem.setText(device.getName());

        textViewPeso.setText("0.0");

        capturaPeso = true;

        buttonCapturaPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (capturaPeso) {

                    String pesoString = textViewPeso.getText().toString();
                    peso = Double.valueOf(pesoString);

                    if(textViewStatus.getText().equals("ESTÁVEL") && (peso > 0D)){
                        textViewStatus.setText("PESO CAPTURADO");
                        textViewStatus.setTextColor(Color.GREEN);
                        capturaPeso = false;
                        buttonCapturaPeso.setText("RECAPTURAR");
                    }
                    else{
                        Toast.makeText(getBaseContext(), "FALHOU A CAPTURA, BALANÇA INSTÁVEL!", Toast.LENGTH_LONG).show();
                    }

                } else {

                    textViewStatus.setText("");
                    capturaPeso = true;
                    buttonCapturaPeso.setText("CAPTURAR");

                }

            }

        });

        buttonAvancaBTPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!capturaPeso) {

                    ppaContext.getPesagemCTR().insItemPes(peso, "", getLatitude(), getLatitude());

                    closeCon();

                    Intent it = new Intent(BTPesoActivity.this, MenuPesagemActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(BTPesoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, CAPTURE A PESAGEM ANTES DE CONTINUAR A PESAGEM.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();

                }

            }

        });

        buttonRetBTPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeCon();

                Intent it = new Intent(BTPesoActivity.this, MenuCaptPesagemActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    protected void onResume() {
        super.onResume();
        new ThreadServidor().start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeCon();
    }

    public void closeCon(){
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
        }
    }

    class ThreadServidor extends Thread {

        @Override
        public void run() {
            try {

                socket = device.createRfcommSocketToServiceRecord(uuid);
                socket.connect();

                in = socket.getInputStream();

                byte[] bytes = new byte[1024];
                int length;

                String peso = "";

                while (in != null) {
                    length = in.read(bytes);
                    String stringRec = new String(bytes, 0, length);
                    peso = peso + stringRec ;
                    if(peso.contains("\n")){
                        if((peso.length() > 12) && (capturaPeso)) {
                            peso = peso.trim() + ",";
                            String[] array = peso.split(",");
                            textViewPeso.setText(array[2]);
                            if (array[3].equals("E")) {
                                textViewStatus.setText("ESTÁVEL");
                                textViewStatus.setTextColor(Color.BLUE);
                            } else {
                                textViewStatus.setText("INSTÁVEL");
                                textViewStatus.setTextColor(Color.RED);
                            }
                        }
                        peso = "";
                    }
                }

            } catch (IOException e) {
                error(e);
            }
        }
    }

    private void error(final IOException e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alerta = new AlertDialog.Builder(BTPesoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO BLUETOOTH. POR FAVOR, CONTINUE A PESAGEM DIGITANDO O PESO.");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeCon();
                        Intent it = new Intent(BTPesoActivity.this, DigPesoActivity.class);
                        startActivity(it);
                        finish();
                    }
                });
                alerta.show();
            }
        });
    }


}
