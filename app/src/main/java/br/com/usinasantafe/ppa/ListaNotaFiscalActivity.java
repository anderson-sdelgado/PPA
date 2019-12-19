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

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.model.bean.estaticas.NotaFiscalBean;
import br.com.usinasantafe.ppa.util.ConexaoWeb;

public class ListaNotaFiscalActivity extends ActivityGeneric {

    private ListView notaFiscalListView;
    private List notaFiscalList;
    private PPAContext ppaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nota_fiscal);

        ppaContext = (PPAContext) getApplication();

        Button buttonAtualNotaFiscal = (Button) findViewById(R.id.buttonAtualNotaFiscal);
        Button buttonRetNotaFiscal = (Button) findViewById(R.id.buttonRetNotaFiscal);

        NotaFiscalBean notaFiscalBean = new NotaFiscalBean();
        notaFiscalList = notaFiscalBean.orderBy("nroNF", true);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < notaFiscalList.size(); i++) {
            notaFiscalBean = (NotaFiscalBean) notaFiscalList.get(i);
            itens.add(notaFiscalBean.getNroNF());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        notaFiscalListView = (ListView) findViewById(R.id.listNotaFiscal);
        notaFiscalListView.setAdapter(adapterList);

        notaFiscalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                NotaFiscalBean notaFiscalBean = (NotaFiscalBean) notaFiscalList.get(position);
                ppaContext.getConfigCTR().setNotaFiscalConfig(notaFiscalBean.getIdNF());

                Intent it = new Intent(ListaNotaFiscalActivity.this, ListaItemNFActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualNotaFiscal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaNotaFiscalActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaNotaFiscalActivity.this)) {

                            progressBar = new ProgressDialog(ListaNotaFiscalActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

//                            pstContext.getAbordagemCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaNotaFiscalActivity.this);
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

        buttonRetNotaFiscal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaNotaFiscalActivity.this, ListaVeiculoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
