package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.model.bean.estaticas.OrdCarregBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.CabPesagemBean;

public class ListaOSActivity extends ActivityGeneric {

    private ListView osListView;
    private PPAContext ppaContext;
    private List<OrdCarregBean> osList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os);

        ppaContext = (PPAContext) getApplication();
        Button buttonRetOS = (Button) findViewById(R.id.buttonRetOS);

        ArrayList<String> itens = new ArrayList<String>();

        osList = ppaContext.getPesagemCTR().osList();
        for(OrdCarregBean ordCarregBean : osList){
            itens.add(String.valueOf(ordCarregBean.getNroOSOrdCarreg()));
        }

        AdapterList adapterList = new AdapterList(this, itens);
        osListView = (ListView) findViewById(R.id.listViewOS);
        osListView.setAdapter(adapterList);

        osListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

            OrdCarregBean ordCarregBean = osList.get(position);
            ppaContext.getPesagemCTR().setItemPesagemBean();
            ppaContext.getPesagemCTR().getItemPesagemBean().setNroOSItemPes(ordCarregBean.getNroOSOrdCarreg());

            osList.clear();

            Intent it = new Intent(ListaOSActivity.this, ListaProdutoActivity.class);
            startActivity(it);
            finish();

            }

        });

        buttonRetOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaOSActivity.this, MenuPesagemActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}