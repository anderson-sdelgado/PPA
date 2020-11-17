package br.com.usinasantafe.ppa.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.model.bean.estaticas.OrdCarregBean;
import br.com.usinasantafe.ppa.model.dao.FuncDAO;
import br.com.usinasantafe.ppa.util.AtualDadosServ;
import br.com.usinasantafe.ppa.model.bean.variaveis.CabPesagemBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;
import br.com.usinasantafe.ppa.model.dao.CabPesagemDAO;
import br.com.usinasantafe.ppa.model.dao.ItemPesagemDAO;
import br.com.usinasantafe.ppa.model.dao.OrgCarregDAO;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;
import br.com.usinasantafe.ppa.util.Imagem;

public class PesagemCTR {

    private ItemPesagemBean itemPesagemBean;

    public PesagemCTR() {
    }

    ///////////////////////////////// VERIFICAR DADOS ////////////////////////////////////////////

    public boolean hasElementsFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    public boolean verCabecPesAberto(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        return cabPesagemDAO.verCabecPesAberto();
    }

    public boolean verStatusConCabecPes(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        if(cabPesagemDAO.getCabPesApont().getStatusConCabPes() == 1){
            return true;
        }
        else{
            return false;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void criarCabecPes(String placaVeicCabPes, Long statusCon){
        ConfigCTR configCTR = new ConfigCTR();
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        cabPesagemDAO.criarCabPesagem(placaVeicCabPes, configCTR.getConfig().getIdEquipConfig(), configCTR.getConfig().getMatricFuncConfig(), statusCon);
    }

    public void insItemPes(Double peso, String comentario, Double latitude, Double logitude){
        itemPesagemBean.setPesoItemPes(peso);
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        itemPesagemDAO.criarItemPesagem(cabPesagemDAO.getCabPesApont().getIdCabPes(), itemPesagemBean, comentario, latitude, logitude);
    }

    public void insItemPes(String comentario, Double latitude, Double logitude){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        itemPesagemDAO.criarItemPesagem(cabPesagemDAO.getCabPesApont().getIdCabPes(), itemPesagemBean, comentario, latitude, logitude);
    }

    public void fechCabPesagem(Bitmap bitmap){
        Imagem imagem = new Imagem();
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        cabPesagemDAO.fechCabPesagem(imagem.getBitmapString(bitmap));
    }

    public boolean verEnvioDados() {
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        return cabPesagemDAO.verCabPesFechado();
    }

    public void verPlacaVeicServ(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OrgCarregDAO orgCarregDAO = new OrgCarregDAO();
        orgCarregDAO.verDados(dado, telaAtual, telaProx, progressDialog);
    }

    public boolean verStatusConPlacaVeic(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        return cabPesagemDAO.verStatusConPlacaVeic();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// GET DADOS /////////////////////////////////////////////

    public List<CabPesagemBean> cabPesagemApontList(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        return cabPesagemDAO.cabPesagApontList();
    }

    public ItemPesagemBean getItemPesagemBean() {
        return itemPesagemBean;
    }

    public List<CabPesagemBean> cabPesagemAbertoList(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        return cabPesagemDAO.cabPesagAbertList();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// SET DADOS /////////////////////////////////////////////

    public void setItemPesagemBean() {
        this.itemPesagemBean = new ItemPesagemBean();
    }

    public void setStatusApontCabPes(CabPesagemBean cabPesagemBean){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        cabPesagemDAO.setStatusApontCabPes(cabPesagemBean);
    }

    public List<OrdCarregBean> osList(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        OrgCarregDAO orgCarregDAO = new OrgCarregDAO();
        return orgCarregDAO.osList(cabPesagemDAO.getCabPesApont().getPlacaVeicCabPes());
    }

    public List<OrdCarregBean> produtoList(){
        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        OrgCarregDAO orgCarregDAO = new OrgCarregDAO();
        return orgCarregDAO.produtoList(cabPesagemDAO.getCabPesApont().getPlacaVeicCabPes(), itemPesagemBean.getNroOSItemPes());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS POR SERVIDOR /////////////////////

    public void atualDadosFunc(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("FuncBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// RECEBER DADOS SERVIDOR ///////////////////////////////////////

    public void deleteCabec(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject cabecJsonObject = new JSONObject(objPrinc);
            JSONArray cabecJsonArray = cabecJsonObject.getJSONArray("cabec");

            for (int i = 0; i < cabecJsonArray.length(); i++) {

                JSONObject objBol = cabecJsonArray.getJSONObject(i);
                Gson gsonBol = new Gson();
                CabPesagemBean cabPesagemBean = gsonBol.fromJson(objBol.toString(), CabPesagemBean.class);

                List cabecList = cabPesagemBean.get("idCabPes", cabPesagemBean.getIdCabPes());
                CabPesagemBean cabPesagemBeanDB = (CabPesagemBean) cabecList.get(0);
                cabecList.clear();

                ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
                List itemPesagemList = itemPesagemBean.get("idCabItemPes", cabPesagemBeanDB.getIdCabPes());

                for (int j = 0; j < itemPesagemList.size(); j++) {

                    itemPesagemBean = (ItemPesagemBean) itemPesagemList.get(j);
                    itemPesagemBean.delete();

                }

                itemPesagemList.clear();
                cabPesagemBeanDB.delete();

            }

        }
        catch(Exception e){
            EnvioDadosServ.getInstance().setEnviando(false);
        }

    }

    public void deleteCabecAberto() {

        CabPesagemDAO cabPesagemDAO = new CabPesagemDAO();
        CabPesagemBean cabPesagemBean = cabPesagemDAO.getCabPesApont();

        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        List itemPesagemList = itemPesagemBean.get("idCabItemPes", cabPesagemBean.getIdCabPes());

        for (int j = 0; j < itemPesagemList.size(); j++) {

            itemPesagemBean = (ItemPesagemBean) itemPesagemList.get(j);
            itemPesagemBean.delete();

        }

        itemPesagemList.clear();
        cabPesagemBean.delete();

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// ENVIO DADOS SERVIDOR ///////////////////////////////////////

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

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
