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

public class ListaProdutoActivity extends ActivityGeneric {

    private ListView produtoListView;
    private PPAContext ppaContext;
    private List<OrdCarregBean> produtoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        ppaContext = (PPAContext) getApplication();
        Button buttonRetProduto = (Button) findViewById(R.id.buttonRetProduto);

        ArrayList<String> itens = new ArrayList<String>();

        produtoList = ppaContext.getPesagemCTR().produtoList();
        for(OrdCarregBean ordCarregBean : produtoList){
            itens.add(ordCarregBean.getCodProdOrdCarreg() + " - " + ppaContext.getPesagemCTR().getOrdCarregProd(ordCarregBean.getCodProdOrdCarreg()).getDescrProdOrdCarreg());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        produtoListView = (ListView) findViewById(R.id.listViewProduto);
        produtoListView.setAdapter(adapterList);

        produtoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                OrdCarregBean ordCarregBean = produtoList.get(position);
                ppaContext.getPesagemCTR().setItemPesagemBean();
                ppaContext.getPesagemCTR().getItemPesagemBean().setProdItemPesagem(ordCarregBean.getCodProdOrdCarreg());

                produtoList.clear();

                if(ppaContext.getPesagemCTR().verOSOrdCarreg()){
                    Intent it = new Intent(ListaProdutoActivity.this, ListaOSActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    ppaContext.getPesagemCTR().getItemPesagemBean().setNroOSItemPesagem(0L);
                    Intent it = new Intent(ListaProdutoActivity.this, MenuCaptPesagemActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

        buttonRetProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaProdutoActivity.this, MenuPesagemActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}