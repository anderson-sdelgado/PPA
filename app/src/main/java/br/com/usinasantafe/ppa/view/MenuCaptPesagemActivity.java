package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class MenuCaptPesagemActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_capt_pesagem);

        Button buttonRetCapturaPesagem = (Button) findViewById(R.id.buttonRetCaptPesagem);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("PESAGEM VIA BLUETOOTH");
        itens.add("PESAGEM MANUAL");

        AdapterList adapterList = new AdapterList(this, itens);
        ListView menuListView = (ListView) findViewById(R.id.listaMenuPesagem);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("PESAGEM VIA BLUETOOTH")) {

                    Intent it = new Intent(MenuCaptPesagemActivity.this, ListaBalancaBTActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("PESAGEM MANUAL")) {

                    Intent it = new Intent(MenuCaptPesagemActivity.this, DigPesoActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonRetCapturaPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuCaptPesagemActivity.this, MenuPesagemActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
