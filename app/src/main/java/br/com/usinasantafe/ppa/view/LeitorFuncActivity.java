package br.com.usinasantafe.ppa.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ppa.util.ConexaoWeb;

public class LeitorFuncActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PPAContext ppaContext;
    private TextView txtRetFunc;
    private ProgressDialog progressBar;
    private FuncBean funcBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_func);

        ppaContext = (PPAContext) getApplication();

        txtRetFunc = (TextView) findViewById(R.id.txtRetFunc);
        Button buttonOkFunc = (Button) findViewById(R.id.buttonOkFunc);
        Button buttonCancFunc = (Button) findViewById(R.id.buttonCancFunc);
        Button buttonDigFunc = (Button) findViewById(R.id.buttonDigFunc);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptMatric = (Button) findViewById(R.id.buttonCaptMatric);

        funcBean = new FuncBean();
        funcBean.setMatricFunc(0L);
        funcBean.setNomeFunc("");

        txtRetFunc.setText("Por Favor, realize a leitura do Cartão do Colaborador Mecânico.");

        buttonOkFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (funcBean.getMatricFunc() > 0) {

                    ppaContext.getPesagemCTR().setCabPesagemBean();
                    ppaContext.getPesagemCTR().getCabPesagemBean().setMatricFuncCabPes(funcBean.getMatricFunc());

                    Intent it = new Intent(LeitorFuncActivity.this, ListaEquipPesagActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorFuncActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonDigFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorFuncActivity.this, DigFuncActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCaptMatric.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorFuncActivity.this, br.com.usinasantafe.ppa.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorFuncActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(LeitorFuncActivity.this)) {

                            progressBar = new ProgressDialog(LeitorFuncActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Colaborador...");
                            progressBar.show();

                            ppaContext.getPesagemCTR().atualDadosFunc(LeitorFuncActivity.this
                                    , LeitorFuncActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorFuncActivity.this);
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

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            String matricula = data.getStringExtra("SCAN_RESULT");
            if (matricula.length() == 8) {
                matricula = matricula.substring(0, 7);
                if (ppaContext.getPesagemCTR().verMatricFunc(Long.parseLong(matricula))) {
                    funcBean = ppaContext.getPesagemCTR().getFunc(Long.parseLong(matricula));
                    txtRetFunc.setText(matricula + "\n" + funcBean.getNomeFunc());
                } else {
                    txtRetFunc.setText("Funcionário Inexistente");
                }
            }
        }

    }

    public void onBackPressed() {
        Intent it = new Intent(LeitorFuncActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}