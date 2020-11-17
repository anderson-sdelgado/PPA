package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class MenuPesagemActivity extends ActivityGeneric {

    private ListView menuPesagemListView;
    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pesagem);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("PESAGEM");
        itens.add("HISTÓRICO");
        itens.add("FECHAR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuPesagemListView = (ListView) findViewById(R.id.listaMenuPesagem);
        menuPesagemListView.setAdapter(adapterList);

        menuPesagemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("PESAGEM")) {

                    if(ppaContext.getPesagemCTR().verStatusConCabecPes()){
                        Intent it = new Intent(MenuPesagemActivity.this, ListaOSActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(MenuPesagemActivity.this, DigOSActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (text.equals("HISTÓRICO")) {

                    Intent it = new Intent(MenuPesagemActivity.this, ListaHistoricoActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("FECHAR")) {

                    Intent it = new Intent(MenuPesagemActivity.this, ListaEquipPesagActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

    }

    public void onBackPressed() {
    }

}

