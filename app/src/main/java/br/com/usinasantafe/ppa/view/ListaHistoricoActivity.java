package br.com.usinasantafe.ppa.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.ppa.PPAContext;
import br.com.usinasantafe.ppa.R;

public class ListaHistoricoActivity extends ActivityGeneric {

    private PPAContext ppaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_historico);

        ppaContext = (PPAContext) getApplication();

        Button buttonRetHistorico = (Button) findViewById(R.id.buttonRetHistorico);

        ListView listaHistorico = (ListView) findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, ppaContext.getPesagemCTR().itemPesagemApontList());
        listaHistorico.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaHistoricoActivity.this, MenuPesagemActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

}