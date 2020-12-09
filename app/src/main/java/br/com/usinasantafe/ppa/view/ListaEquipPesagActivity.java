package br.com.usinasantafe.ppa.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.CabecPesagemBean;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;

public class ListaEquipPesagActivity extends ActivityGeneric {

    private ListView equipPesagListView;
    private PPAContext ppaContext;
    private ArrayList<CabecPesagemBean> cabPesagemList;
    private TextView textViewProcesso;
    private TextView textViewMotorista;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equip_pesag);

        ppaContext = (PPAContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);
        textViewMotorista = (TextView) findViewById(R.id.textViewMotorista);
        Button buttonInserirEquipPesagem = (Button) findViewById(R.id.buttonInserirEquipPesagem);
        Button buttonInserirOperadorPesagem = (Button) findViewById(R.id.buttonInserirOperadorPesagem);
        Button buttonRetEquipPesagem = (Button) findViewById(R.id.buttonRetEquipPesagem);

        customHandler.postDelayed(updateTimerThread, 0);

        if(ppaContext.getConfigCTR().getConfig().getMatricFuncConfig() > 0L){
            FuncBean funcBean = ppaContext.getConfigCTR().getFunc();
            textViewMotorista.setText(funcBean.getMatricFunc() + " - " + funcBean.getNomeFunc());
        }
        else{
            textViewMotorista.setText("Por Favor, insira um operador!");
        }

        buttonInserirEquipPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ppaContext.getConfigCTR().getConfig().getMatricFuncConfig() > 0L) {
                    Intent it = new Intent(ListaEquipPesagActivity.this, DigPlacaVeicActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEquipPesagActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, INSIRA MATRÍCULA DO OPERADOR PARA APONTAR AS PESAGENS.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                }
            }
        });

        buttonInserirOperadorPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaEquipPesagActivity.this, LeitorFuncActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonRetEquipPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!ppaContext.getPesagemCTR().verCabecPesagemAberto()){
                    Intent it = new Intent(ListaEquipPesagActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEquipPesagActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("PESAGEM NÃO FINALIZADAS! POR FAVOR, FINALIZE TODAS AS PESAGENS ANTES DE RETORNAR AO MENU INICIAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    alerta.show();
                }

            }
        });

        ArrayList<String> itens = new ArrayList<String>();

        if(ppaContext.getPesagemCTR().verCabecPesagemAberto()){
            cabPesagemList = (ArrayList<CabecPesagemBean>) ppaContext.getPesagemCTR().cabPesagemAbertoList();
        }
        else{
            cabPesagemList = new ArrayList<CabecPesagemBean>();
            CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
            cabecPesagemBean.setPlacaVeicCabecPesagem("NÃO CONTÉM VEÍCULO");
            cabecPesagemBean.setStatusConCabecPesagem(2L);
            cabPesagemList.add(cabecPesagemBean);
        }

        AdapterListEquip adapterList = new AdapterListEquip(this, cabPesagemList);
        equipPesagListView = (ListView) findViewById(R.id.listViewEquipPesag);
        equipPesagListView.setAdapter(adapterList);

        equipPesagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if(ppaContext.getPesagemCTR().verCabecPesagemAberto()) {

                    CabecPesagemBean cabecPesagemBean = (CabecPesagemBean) cabPesagemList.get(position);
                    cabPesagemList.clear();

                    ppaContext.getPesagemCTR().setStatusApontCabPes(cabecPesagemBean);

                    Intent it = new Intent(ListaEquipPesagActivity.this, MenuPesagemActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            int status = EnvioDadosServ.getInstance().getStatusEnvio();
            if (status == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            }
            else if (status == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            }
            else if (status == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

}