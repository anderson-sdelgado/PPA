package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;
import br.com.usinasantafe.ppa.util.Tempo;

public class ItemPesagemDAO {

    public ItemPesagemDAO() {
    }

    public void criarItemPesagem(Long idCab, ItemPesagemBean itemPesagemBean, String coment, Double latitude, Double longitude){
        itemPesagemBean.setIdCabItemPes(idCab);
        itemPesagemBean.setComentFalhaItemPes(coment);
        itemPesagemBean.setDthrItemPes(Tempo.getInstance().dataComHora());
        itemPesagemBean.setLatitudeItemPes(latitude);
        itemPesagemBean.setLongitudeItemPes(longitude);
        itemPesagemBean.insert();
    }

    public List getListItemCabec(Long idCabec){
        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        return itemPesagemBean.get("idCabItemPes", idCabec);
    }

    public void delItemCabec(Long idCabec){
        List itemPesagemList = getListItemCabec(idCabec);
        for (int i = 0; i < itemPesagemList.size(); i++) {
            ItemPesagemBean itemPesagemBean = (ItemPesagemBean) itemPesagemList.get(i);
            itemPesagemBean.delete();
        }
    }

}
