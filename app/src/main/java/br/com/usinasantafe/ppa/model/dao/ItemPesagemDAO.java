package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;
import br.com.usinasantafe.ppa.util.Tempo;

public class ItemPesagemDAO {

    public ItemPesagemDAO() {
    }

    public void criarItemPesagem(Long idCab, ConfigBean configBean, Double pesagem, String coment, Double latitude, Double longitude){
        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        itemPesagemBean.setIdCabItemPes(idCab);
        itemPesagemBean.setNroNFItemPes(configBean.getNotaFiscalConfig());
        itemPesagemBean.setCodItNFItemPes(configBean.getItemNFConfig());
        itemPesagemBean.setValorItemPes(pesagem);
        itemPesagemBean.setComentFalhaItemPes(coment);
        itemPesagemBean.setDthrItemPes(Tempo.getInstance().dataComHora());
        itemPesagemBean.setLatitudeItemPes(latitude);
        itemPesagemBean.setLongitudeItemPes(longitude);
        itemPesagemBean.setStatusConOSCabPes(configBean.getStatusConOSConfig());
        itemPesagemBean.insert();
    }

    public List getListItemCabec(Long idCabec){
        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        return itemPesagemBean.get("idCabItemPes", idCabec);
    }

    public void delItemCabec(Long idCabec){
        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        List itemPesagemList = getListItemCabec(idCabec);
        for (int i = 0; i < itemPesagemList.size(); i++) {
            itemPesagemBean = (ItemPesagemBean) itemPesagemList.get(i);
            itemPesagemBean.delete();
        }
    }

}
