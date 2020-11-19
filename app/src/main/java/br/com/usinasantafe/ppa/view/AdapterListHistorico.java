package br.com.usinasantafe.ppa.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.control.PesagemCTR;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;


/**
 * Created by anderson on 08/03/2018.
 */

public class AdapterListHistorico extends BaseAdapter {

    private List<ItemPesagemBean> itens;
    private LayoutInflater layoutInflater;
    private Long tipoEquip;
    private TextView textViewHistDataHora;
    private TextView textViewHistOS;
    private TextView textViewHistProd;
    private TextView textViewHistPeso;

    public AdapterListHistorico(Context context, List itens, Long tipoEquip) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
        this.tipoEquip = tipoEquip;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_historico, null);
        textViewHistDataHora = (TextView) view.findViewById(R.id.textViewHistDataHora);
        textViewHistOS = (TextView) view.findViewById(R.id.textViewHistOrdemServico);
        textViewHistProd = (TextView) view.findViewById(R.id.textViewHistProd);
        textViewHistPeso = (TextView) view.findViewById(R.id.textViewHistPeso);

        ItemPesagemBean itemPesagemBean = itens.get(position);

        textViewHistDataHora.setText("DATA/HORA: " + itemPesagemBean.getDthrItemPes());
        textViewHistOS.setText("O.S.:" + itemPesagemBean.getNroOSItemPes());
        PesagemCTR pesagemCTR = new PesagemCTR();
        textViewHistProd.setText("PRODUTO: " + itemPesagemBean.getProdItemPes() + " - " + pesagemCTR.getOrdCarregProd(itemPesagemBean.getProdItemPes()).getDescrProdOrdCarreg());
        textViewHistPeso.setText("PESO: " + itemPesagemBean.getProdItemPes());

        return view;

    }

}