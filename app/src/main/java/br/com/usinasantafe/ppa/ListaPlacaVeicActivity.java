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

import br.com.usinasantafe.ppa.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ppa.util.ConexaoWeb;

public class ListaPlacaVeicActivity extends ActivityGeneric {

    private ListView veiculoListView;
    private List equipList;
    private PPAContext ppaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_placa_veic);

        ppaContext = (PPAContext) getApplication();

        Button buttonAtualVeiculo = (Button) findViewById(R.id.buttonAtualVeiculo);
        Button buttonRetVeiculo = (Button) findViewById(R.id.buttonRetVeiculo);

        EquipBean equipBean = new EquipBean();
        equipList = equipBean.orderBy("placaEquip", true);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < equipList.size(); i++) {
            equipBean = (EquipBean) equipList.get(i);
            itens.add(equipBean.getPlacaEquip());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        veiculoListView = (ListView) findViewById(R.id.listVeiculo);
        veiculoListView.setAdapter(adapterList);

        veiculoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String placa = textView.getText().toString();

                ppaContext.getPesagemCTR().criarCabecPes(placa);

                Intent it = new Intent(ListaPlacaVeicActivity.this, ListaNotaFiscalActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaPlacaVeicActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaPlacaVeicActivity.this)) {

                            progressBar = new ProgressDialog(ListaPlacaVeicActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

//                            pstContext.getAbordagemCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaPlacaVeicActivity.this);
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

        buttonRetVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaPlacaVeicActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
