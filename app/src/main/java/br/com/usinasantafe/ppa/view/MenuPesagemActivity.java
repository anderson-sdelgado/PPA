package br.com.usinasantafe.ppa.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class MenuPesagemActivity extends ActivityGeneric {

    private ListView menuPesagemListView;
    private PPAContext ppaContext;
    private TextView textViewPesagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pesagem);

        ppaContext = (PPAContext) getApplication();
        Button buttonRetPesagem = (Button) findViewById(R.id.buttonRetPesagem);
        textViewPesagem = (TextView) findViewById(R.id.textViewPesagem);

        textViewPesagem.setText("VEÍCULO: " + ppaContext.getPesagemCTR().getCabecPesagemApont().getPlacaVeicCabecPesagem());

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("PESAGEM");
        itens.add("HISTÓRICO DE PESAGEM");
        itens.add("FINALIZAR PESAGEM");
        itens.add("EXCLUIR PESAGEM");

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

                    if(ppaContext.getPesagemCTR().verStatusConCabecPesagem()){
                        Intent it = new Intent(MenuPesagemActivity.this, ListaProdutoActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(MenuPesagemActivity.this, DigProdutoActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (text.equals("HISTÓRICO DE PESAGEM")) {

                    Intent it = new Intent(MenuPesagemActivity.this, ListaHistoricoActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("FINALIZAR PESAGEM")) {

                    if(ppaContext.getPesagemCTR().verPorcentualPesagem()){

                        ppaContext.getPesagemCTR().fechCabecPesagem();

                        Intent it = new Intent(MenuPesagemActivity.this, ListaEquipPesagActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPesagemActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("PESAGEM TOTAL ESTA INCOMPATÍVEL COM A NOTA FISCAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ppaContext.getPesagemCTR().fechCabecPesagem();

                                Intent it = new Intent(MenuPesagemActivity.this, ListaEquipPesagActivity.class);
                                startActivity(it);
                                finish();

                            }
                        });
                        alerta.show();

                    }

                } else if (text.equals("EXCLUIR PESAGEM")) {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPesagemActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE EXCLUIR PESAGEM(NS)?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ppaContext.getPesagemCTR().deleteCabecApont();

                            Intent it = new Intent(MenuPesagemActivity.this, ListaEquipPesagActivity.class);
                            startActivity(it);
                            finish();

                        }
                    });

                    alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();

                }

            }

        });

        buttonRetPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPesagemActivity.this, ListaEquipPesagActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}

