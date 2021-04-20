package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class ListaHistDetalhadoActivity extends ActivityGeneric {

    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hist_detalhado);

        ppaContext = (PPAContext) getApplication();

        Button buttonRetHistDetalhado = (Button) findViewById(R.id.buttonRetHistDetalhado);
        Button buttonAvancarHistDetalhado = (Button) findViewById(R.id.buttonAvancarHistDetalhado);

        ListView listaHistDetalhado = (ListView) findViewById(R.id.listaHistDetalhado);
        AdapterListHistDetalhado adapterListHistDetalhado = new AdapterListHistDetalhado(this, ppaContext.getPesagemCTR().itemPesagemApontList());
        listaHistDetalhado.setAdapter(adapterListHistDetalhado);

        buttonAvancarHistDetalhado.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaHistDetalhadoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonRetHistDetalhado.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaHistDetalhadoActivity.this, ListaHistoricoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}