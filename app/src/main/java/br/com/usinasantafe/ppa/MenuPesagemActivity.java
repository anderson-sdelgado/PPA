package br.com.usinasantafe.ppa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuPesagemActivity extends ActivityGeneric {

    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pesagem);

        Button buttonRetCapturaPesagem = (Button) findViewById(R.id.buttonRetCapturaPesagem);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("CAPTURA PESAGEM VIA BLUETOOTH");
        itens.add("DIGITAR VALOR DE PESAGEM");

        AdapterList adapterList = new AdapterList(this, itens);
        ListView menuListView = (ListView) findViewById(R.id.listaMenuPesagem);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("CAPTURA PESAGEM VIA BLUETOOTH")) {

                    Intent it = new Intent(MenuPesagemActivity.this, ListaBalancaBTActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("DIGITAR VALOR DE PESAGEM")) {

                    Intent it = new Intent(MenuPesagemActivity.this, DigPesoActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonRetCapturaPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPesagemActivity.this, DigOSActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
