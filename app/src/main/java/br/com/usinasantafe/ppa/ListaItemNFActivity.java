package br.com.usinasantafe.ppa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.model.bean.estaticas.ItemNFBean;
import br.com.usinasantafe.ppa.util.ConexaoWeb;

public class ListaItemNFActivity extends ActivityGeneric {

    private ListView itemNFListView;
    private List itemNFList;
    private PPAContext ppaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item_nf);

        ppaContext = (PPAContext) getApplication();

        Button buttonAtualItemNF = (Button) findViewById(R.id.buttonAtualItemNF);
        Button buttonRetItemNF = (Button) findViewById(R.id.buttonRetItemNF);

        ItemNFBean itemNFBean = new ItemNFBean();
        itemNFList = itemNFBean.orderBy("codProd", true);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < itemNFList.size(); i++) {
            itemNFBean = (ItemNFBean) itemNFList.get(i);
            itens.add(itemNFBean.getCodProd());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        itemNFListView = (ListView) findViewById(R.id.listItemNF);
        itemNFListView.setAdapter(adapterList);

        itemNFListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String itemNF = textView.getText().toString();

                ppaContext.getConfigCTR().setItemNFConfig(itemNF);

                Intent it = new Intent(ListaItemNFActivity.this, DigOSActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualItemNF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaItemNFActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaItemNFActivity.this)) {

                            progressBar = new ProgressDialog(ListaItemNFActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

//                            pstContext.getAbordagemCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaItemNFActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();

                        }

                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerta.show();

            }

        });

        buttonRetItemNF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaItemNFActivity.this, ListaNotaFiscalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
