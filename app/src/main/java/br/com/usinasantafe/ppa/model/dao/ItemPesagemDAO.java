package br.com.usinasantafe.ppa.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;
import br.com.usinasantafe.ppa.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.ppa.util.Tempo;

public class ItemPesagemDAO {

    public ItemPesagemDAO() {
    }

    public void criarItemPesagem(Long idCab, Long matricFunc, ItemPesagemBean itemPesagemBean, String coment, Double latitude, Double longitude){
        itemPesagemBean.setIdCabItemPesagem(idCab);
        itemPesagemBean.setMatricFuncItemPesagem(matricFunc);
        itemPesagemBean.setComentFalhaItemPesagem(coment);
        itemPesagemBean.setDthrItemPesagem(Tempo.getInstance().dataComHora());
        itemPesagemBean.setLatitudeItemPesagem(latitude);
        itemPesagemBean.setLongitudeItemPesagem(longitude);
        itemPesagemBean.insert();
    }

    public List<ItemPesagemBean> getListItem(Long idCabec){
        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        return itemPesagemBean.get("idCabItemPes", idCabec);
    }

    public List<ItemPesagemBean> getListItem(Long idCabec, Long idBDOrdCarreg){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabec(idCabec));
        pesqArrayList.add(getPesqOrdCarreg(idBDOrdCarreg));
        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        return itemPesagemBean.get(pesqArrayList);
    }

    public void delItemCabec(Long idCabec){
        List<ItemPesagemBean> itemPesagemList = getListItem(idCabec);
        for (ItemPesagemBean itemPesagemBean : itemPesagemList) {
            itemPesagemBean.delete();
        }
    }

    public boolean verItemPesagemManual(Long idCabec){
        boolean ver = false;
        List<ItemPesagemBean> itemPesagemList = getListItem(idCabec);
        for (ItemPesagemBean itemPesagemBean : itemPesagemList) {
            if(!itemPesagemBean.getComentFalhaItemPesagem().equals("")){
                ver = true;
            }
        }
        return ver;
    }

    public Double verItemTotalPesagem(Long idCabec, Long idBDOrdCarreg){
        Double totalPes = 0D;
        List<ItemPesagemBean> itemPesagemList = getListItem(idCabec, idBDOrdCarreg);
        if(itemPesagemList.size() > 0){
            for (ItemPesagemBean itemPesagemBean : itemPesagemList) {
                totalPes = itemPesagemBean.getPesoItemPesagem() + totalPes;
            }
        }
        itemPesagemList.clear();
        return totalPes;
    }

    private EspecificaPesquisa getPesqCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabItemPes");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqOrdCarreg(Long idBDOrdCarreg){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idOrdCarregItemPes");
        pesquisa.setValor(idBDOrdCarreg);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
