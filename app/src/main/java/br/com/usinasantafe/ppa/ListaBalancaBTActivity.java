package br.com.usinasantafe.ppa;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListaBalancaBTActivity extends ActivityGeneric {

    protected BluetoothAdapter  btfAdapter;
    protected List<BluetoothDevice> listBtd;
    private ListView listBalancaBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_balanca_bt);

        btfAdapter = BluetoothAdapter.getDefaultAdapter();
        listBalancaBT = (ListView) findViewById(R.id.listBalancaBT);

        if (btfAdapter == null) {

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBalancaBTActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("BLUETOOTH NÃO DISPONÍVEL NESTE APARELHO! POR FAVOR, DER ANDAMENTO AO PROCESSO DIGITANDO A PESAGEM.");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it = new Intent(ListaBalancaBTActivity.this, DigPesagemActivity.class);
                    startActivity(it);
                    finish();
                }
            });

            alerta.show();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (btfAdapter.isEnabled()) {
            Toast.makeText(this, "BLUETOOTH LIGADO!", Toast.LENGTH_LONG).show();

            if (btfAdapter != null) {

                Set<BluetoothDevice> pareados = btfAdapter.getBondedDevices();
                listBtd = new ArrayList<BluetoothDevice>(pareados);

                updateLista();
            }

        } else {
            //  <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, 0);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Aqui você pode conferir se o usuário ativou ou não o bluetooth
        if (resultCode != Activity.RESULT_OK) {

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBalancaBTActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("FALHA NO ATIVAMENTO DO BLUETOOTH! POR FAVOR, DER ANDAMENTO AO PROCESSO DIGITANDO A PESAGEM.");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it = new Intent(ListaBalancaBTActivity.this, DigPesagemActivity.class);
                    startActivity(it);
                    finish();
                }
            });

            alerta.show();

            Toast.makeText(this, "O bluetooth não foi ativado.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void updateLista() {
        // Cria o array com o nome de cada device
        List<String> nomes = new ArrayList<String>();
        for (BluetoothDevice device : listBtd) {
            nomes.add(device.getName());
        }

        // Cria o adapter para popular o ListView
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, nomes);
        listBalancaBT.setAdapter(adapter);
        listBalancaBT.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int pos,
                                    long id) {

                BluetoothDevice device = listBtd.get(pos);

                Intent intent = new Intent(ListaBalancaBTActivity.this, BTPesagemActivity.class);
                intent.putExtra(BluetoothDevice.EXTRA_DEVICE, device);
                startActivity(intent);

            }

        });
    }

}
