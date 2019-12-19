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

import br.com.usinasantafe.ppa.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ppa.util.ConexaoWeb;

public class ListaOSActivity extends ActivityGeneric {

    private ListView osListView;
    private List osList;
    private PPAContext ppaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os);

        ppaContext = (PPAContext) getApplication();

        Button buttonAtualOS = (Button) findViewById(R.id.buttonAtualOS);
        Button buttonRetOS = (Button) findViewById(R.id.buttonRetOS);

        OSBean osBean = new OSBean();
        osList = osBean.orderBy("nroOS", true);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < osList.size(); i++) {
            osBean = (OSBean) osList.get(i);
            itens.add(osBean.getNroOS());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        osListView = (ListView) findViewById(R.id.listOS);
        osListView.setAdapter(adapterList);

        osListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                OSBean osBean = (OSBean) osList.get(position);
                ppaContext.getConfigCTR().setOSConfig(osBean.getIdOS());

                Intent it = new Intent(ListaOSActivity.this, BTPesagemActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOSActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaOSActivity.this)) {

                            progressBar = new ProgressDialog(ListaOSActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

//                            pstContext.getAbordagemCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOSActivity.this);
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

        buttonRetOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaOSActivity.this, ListaItemNFActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
