package br.com.usinasantafe.ppa.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.ppa.R;
import br.com.usinasantafe.ppa.model.bean.variaveis.CabecPesagemBean;

/**
 * Created by anderson on 19/10/2015.
 */
public class AdapterListEquip extends BaseAdapter {

    private ArrayList<CabecPesagemBean> itens;
    private LayoutInflater layoutInflater;

    public AdapterListEquip(Context context, ArrayList<CabecPesagemBean> itens) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_item_lista, null);
        TextView textView = (TextView) convertView.findViewById(R.id.textViewItemList);
        CabecPesagemBean cabecPesagemBean = itens.get(position);
        textView.setText(cabecPesagemBean.getPlacaVeicCabecPesagem());
        if(cabecPesagemBean.getStatusConCabecPesagem() == 0L){
            textView.setTextColor(Color.RED);
        }
        else if(cabecPesagemBean.getStatusConCabecPesagem() == 1L){
            textView.setTextColor(Color.BLUE);
        }
        else if(cabecPesagemBean.getStatusConCabecPesagem() == 2L){
            textView.setTextColor(Color.BLACK);
        }

        return convertView;
    }
}
