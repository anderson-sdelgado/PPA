package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.model.bean.estaticas.OrdCarregBean;

public class ListaNotaFiscalActivity extends ActivityGeneric {

    private ListView nfListView;
    private PPAContext ppaContext;
    private ArrayList<OrdCarregBean> nfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nota_fiscal);

        ppaContext = (PPAContext) getApplication();
        Button buttonRetNF = (Button) findViewById(R.id.buttonRetNF);

        ArrayList<String> itens = new ArrayList<String>();

        nfList = ppaContext.getPesagemCTR().ordCarregArrayList();
        for(OrdCarregBean ordCarregBean : nfList){
            itens.add(String.valueOf(ordCarregBean.getNroNFOrdCarreg()));
        }

        AdapterList adapterList = new AdapterList(this, itens);
        nfListView = (ListView) findViewById(R.id.listViewNF);
        nfListView.setAdapter(adapterList);

        nfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                OrdCarregBean ordCarregBean = nfList.get(position);
                Log.i("PPA", "IdBDOrdCarreg = " + ordCarregBean.getIdBDOrdCarreg());
                ppaContext.getPesagemCTR().abrirCabecPesagem(ordCarregBean.getIdBDOrdCarreg());

                nfList.clear();

                Intent it = new Intent(ListaNotaFiscalActivity.this, ListaEquipPesagActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetNF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaNotaFiscalActivity.this, DigPlacaVeicActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}