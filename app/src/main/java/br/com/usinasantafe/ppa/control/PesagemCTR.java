package br.com.usinasantafe.ppa.control;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import br.com.usinasantafe.ppa.DigOSActivity;
import br.com.usinasantafe.ppa.DigPlacaVeicActivity;
import br.com.usinasantafe.ppa.model.bean.variaveis.CabPesagemBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;
import br.com.usinasantafe.ppa.model.dao.CabPesagemDAO;
import br.com.usinasantafe.ppa.model.dao.ItemPesagemDAO;
import br.com.usinasantafe.ppa.model.dao.OSDAO;
import br.com.usinasantafe.ppa.model.dao.PlacaVeicDAO;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;
import br.com.usinasantafe.ppa.util.Imagem;

public class PesagemCTR {

    public PesagemCTR() {
    }

    public void criarCabecPes(String placaVeicCabPes, Long statusCon){
        ConfigCTR configCTR = new ConfigCTR();
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        cabPesagemDAO.criarCabPesagem(placaVeicCabPes, configCTR.getConfig().getMatricFuncConfig(), statusCon);
    }

    public void insItemPes(Double pesagem, String comentario, Double latitude, Double logitude){
        ConfigCTR configCTR = new ConfigCTR();
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        itemPesagemDAO.criarItemPesagem(cabPesagemDAO.getCabPesagem().getIdCabPes(), configCTR.getConfig(), pesagem, comentario, latitude, logitude);
    }

    public void insItemPes(Double pesagem, Double latitude, Double logitude){
        ConfigCTR configCTR = new ConfigCTR();
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        itemPesagemDAO.criarItemPesagem(cabPesagemDAO.getCabPesagem().getIdCabPes(), configCTR.getConfig(), pesagem, "", latitude, logitude);
    }

    public void fechCabPesagem(Bitmap bitmap){
        Imagem imagem = new Imagem();
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        cabPesagemDAO.fechCabPesagem(imagem.getBitmapString(bitmap));
    }

    public String dadosCabecFechEnvio() {
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        CabPesagemBean cabPesagemBean = cabPesagemDAO.getCabPesFechado();
        JsonArray jsonArrayCabec = new JsonArray();
        Gson gsonCabec = new Gson();
        jsonArrayCabec.add(gsonCabec.toJsonTree(cabPesagemBean, cabPesagemBean.getClass()));
        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);
        return jsonCabec.toString();
    }

    public String dadosItemFechEnvio() {
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        List itemAbordList = itemPesagemDAO.getListItemCabec(cabPesagemDAO.getCabPesFechado().getIdCabPes());

        JsonArray jsonArrayItem = new JsonArray();
        for (int i = 0; i < itemAbordList.size(); i++) {
            ItemPesagemBean itemPesagemBean = (ItemPesagemBean) itemAbordList.get(i);
            Gson gsonItem = new Gson();
            jsonArrayItem.add(gsonItem.toJsonTree(itemPesagemBean, itemPesagemBean.getClass()));
        }

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);
        return jsonItem.toString();
    }

    public boolean verEnvioDados() {
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        return cabPesagemDAO.verCabPesFechado();
    }

    public void deleteCabec(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            Long idCabec = Long.valueOf(retorno.substring(pos1));

            CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
            cabPesagemDAO.delCabec(idCabec);

            ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
            itemPesagemDAO.delItemCabec(idCabec);

            EnvioDadosServ.getInstance().envioDados();

        }
        catch(Exception e){
            EnvioDadosServ.getInstance().setEnviando(false);
        }

    }

    public void verPlacaVeicServ(String dado, DigPlacaVeicActivity digPlacaVeicActivity){
        PlacaVeicDAO placaVeicDAO = new PlacaVeicDAO();
        placaVeicDAO.verDados(dado, digPlacaVeicActivity);
    }

    public boolean verPlacaVeicBD(String placa){
        PlacaVeicDAO placaVeicDAO = new PlacaVeicDAO();
        return placaVeicDAO.verBD(placa);
    }

    public void verOSServ(String dado, DigOSActivity digOSActivity){
        OSDAO osDAO = new OSDAO();
        osDAO.verDados(dado, digOSActivity);
    }

    public boolean verOSBD(String placa){
        PlacaVeicDAO placaVeicDAO = new PlacaVeicDAO();
        return placaVeicDAO.verBD(placa);
    }

    public boolean verStatusConPlacaVeic(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        return cabPesagemDAO.verStatusConPlacaVeic();
    }

}
