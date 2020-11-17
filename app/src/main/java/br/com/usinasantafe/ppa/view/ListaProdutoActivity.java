package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.model.bean.estaticas.OrdCarregBean;

public class ListaProdutoActivity extends AppCompatActivity {

    private ListView produtoListView;
    private PPAContext ppaContext;
    private List<OrdCarregBean> produtoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        ppaContext = (PPAContext) getApplication();

        ArrayList<String> itens = new ArrayList<String>();

        produtoList = ppaContext.getPesagemCTR().produtoList();
        for(OrdCarregBean ordCarregBean : produtoList){
            itens.add(ordCarregBean.getProdOrdCarreg());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        produtoListView = (ListView) findViewById(R.id.listaMenuInicial);
        produtoListView.setAdapter(adapterList);

        produtoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                OrdCarregBean ordCarregBean = produtoList.get(position);
                ppaContext.getPesagemCTR().getItemPesagemBean().setProdItemPes(ordCarregBean.getProdOrdCarreg());

                produtoList.clear();

                Intent it = new Intent(ListaProdutoActivity.this, MenuCaptPesagemActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed() {
    }

}