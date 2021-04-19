package br.com.usinasantafe.ppa.control;

import android.app.ProgressDialog;
import android.content.Context;

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
import br.com.usinasantafe.ppa.model.bean.variaveis.CabecPesagemBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;
import br.com.usinasantafe.ppa.model.dao.CabecPesagemDAO;
import br.com.usinasantafe.ppa.model.dao.ItemPesagemDAO;
import br.com.usinasantafe.ppa.model.dao.OrdCarregDAO;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;
import br.com.usinasantafe.ppa.util.Tempo;

public class PesagemCTR {

    private ItemPesagemBean itemPesagemBean;

    public PesagemCTR() {
    }

    ///////////////////////////////// VERIFICAR DADOS ////////////////////////////////////////////

    public boolean hasElementsFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    public boolean verCabecPesagemAberto(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        return cabecPesagemDAO.verCabecPesAberto();
    }

    public boolean verStatusConCabecPesagem(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        if(cabecPesagemDAO.getCabecPesagemApont().getStatusConCabecPesagem() == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean verOrdCarreg(String placa){
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        return ordCarregDAO.verOrdCarreg(placa);
    }

    public boolean verItemPesManual(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        return itemPesagemDAO.verItemPesagemManual(cabecPesagemDAO.getCabecPesagemApont().getIdCabecPesagem());
    }

    public boolean verPorcentualPesagem(){
        boolean ret = true;
        if(verStatusConCabecPesagem()){
            CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
            ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
            OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
            List<OrdCarregBean> ordCarregList = ordCarregDAO.ordCarregList(cabecPesagemDAO.getCabecPesagemApont().getPlacaVeicCabecPesagem());
            for(OrdCarregBean ordCarregBean : ordCarregList){
                Double totalPesagem = itemPesagemDAO.verItemTotalPesagem(cabecPesagemDAO.getCabecPesagemApont().getIdCabecPesagem(), ordCarregBean.getCodProdOrdCarreg());
                Double pesagemMinino = ordCarregBean.getPesoProdOrdCarreg() - ((ordCarregBean.getPesoProdOrdCarreg() / 100) * 10);
                Double pesagemMaximo = ordCarregBean.getPesoProdOrdCarreg() + ((ordCarregBean.getPesoProdOrdCarreg() / 100) * 10);
                if(totalPesagem > 0D){
                    if((totalPesagem < pesagemMinino) || (totalPesagem > pesagemMaximo)){
                        ret = false;
                    }
                }
            }
        }
        return ret;
    }

    public boolean verQtdeOrdCarreg(String placa){
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        return ordCarregDAO.verQtdeOrdCarreg(placa);
    }

    public boolean verOSOrdCarreg(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        return ordCarregDAO.verOSOrdCarreg(cabecPesagemDAO.getCabecPesagemApont().getIdOrdCarregCabecPesagem(), itemPesagemBean.getProdItemPesagem());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// SALVAR/ATUALIZAR/EXCLUIR DADOS /////////////////////////////////

    public void criarCabecPesagem(String placa, Long statusCon){
        ConfigCTR configCTR = new ConfigCTR();
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        cabecPesagemDAO.criarCabecPesagem(placa, configCTR.getConfig().getIdEquipConfig(), statusCon);
        if(statusCon == 0L){
            cabecPesagemDAO.abrirCabecPesagem(0L);
        }
    }

    public void abrirCabecPesagem(){
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        cabecPesagemDAO.abrirCabecPesagem(ordCarregDAO.getOrdCarregPlaca(cabecPesagemDAO.getCabecPesagemCriado().getPlacaVeicCabecPesagem()).getIdBDOrdCarreg());
    }

    public void abrirCabecPesagem(Long idOrdCarreg){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        cabecPesagemDAO.abrirCabecPesagem(idOrdCarreg);
    }

    public void insItemPesagem(Double peso, String comentario, Double latitude, Double logitude){
        itemPesagemBean.setPesoItemPesagem(peso);
        ConfigCTR configCTR = new ConfigCTR();
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        itemPesagemDAO.criarItemPesagem(cabecPesagemDAO.getCabecPesagemApont().getIdCabecPesagem(), configCTR.getConfig().getMatricFuncConfig(), itemPesagemBean, comentario, latitude, logitude);
    }

    public void insItemPesagem(String comentario, Double latitude, Double logitude){
        ConfigCTR configCTR = new ConfigCTR();
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        itemPesagemDAO.criarItemPesagem(cabecPesagemDAO.getCabecPesagemApont().getIdCabecPesagem(), configCTR.getConfig().getMatricFuncConfig(), itemPesagemBean, comentario, latitude, logitude);
    }

    public void fechCabecPesagem(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        cabecPesagemDAO.fecharCabecPesagem();
    }

    public boolean verEnvioDados() {
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        return cabecPesagemDAO.verCabecPesagemFechado();
    }

    public void verPlacaVeicServ(String dado, Context telaAtual, Class telaProx1, Class telaProx2, ProgressDialog progressDialog){
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        ordCarregDAO.verDados(dado, telaAtual, telaProx1, telaProx2, progressDialog);
    }

    public boolean verStatusConPlacaVeic(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        return cabecPesagemDAO.verStatusConPlacaVeic();
    }

    public void deleteDataDif(){
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        ordCarregDAO.deleteDataDif(Tempo.getInstance().dataSHora());
    }

    public void delCabecCriado(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        cabecPesagemDAO.delCabecCriado();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// GET DADOS /////////////////////////////////////////////

    public List<ItemPesagemBean> itemPesagemApontArrayList(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        List<CabecPesagemBean> cabPesagemList = cabecPesagemDAO.cabecPesagemApontList();
        CabecPesagemBean cabecPesagemBean = cabPesagemList.get(0);
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        List<ItemPesagemBean> itemPesagemList = itemPesagemDAO.getListItemArrayList(cabecPesagemBean.getIdCabecPesagem());
        cabPesagemList.clear();
        return itemPesagemList;
    }

    public List<ItemPesagemBean> itemPesagemApontList(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        List<CabecPesagemBean> cabPesagemList = cabecPesagemDAO.cabecPesagemApontList();
        CabecPesagemBean cabecPesagemBean = cabPesagemList.get(0);
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        List<ItemPesagemBean> itemPesagemList = itemPesagemDAO.getListItem(cabecPesagemBean.getIdCabecPesagem());
        cabPesagemList.clear();
        return itemPesagemList;
    }

    public ItemPesagemBean getItemPesagemBean() {
        return itemPesagemBean;
    }

    public List<CabecPesagemBean> cabPesagemAbertoList(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        return cabecPesagemDAO.cabecPesagemAbertList();
    }

    public OrdCarregBean getOrdCarregProd(String codProd){
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        return ordCarregDAO.getOrdCarregProd(codProd);
    }

    public CabecPesagemBean getCabecPesagemApont(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        List<CabecPesagemBean> cabPesagemList = cabecPesagemDAO.cabecPesagemApontList();
        CabecPesagemBean cabecPesagemBean = cabPesagemList.get(0);
        cabPesagemList.clear();
        return cabecPesagemBean;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// SET DADOS /////////////////////////////////////////////

    public void setItemPesagemBean() {
        this.itemPesagemBean = new ItemPesagemBean();
    }

    public void setStatusApontCabPes(CabecPesagemBean cabecPesagemBean){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        cabecPesagemDAO.setStatusApontCabecPesagem(cabecPesagemBean);
    }

    public List<OrdCarregBean> osList(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        return ordCarregDAO.ordCarregList(cabecPesagemDAO.getCabecPesagemApont().getIdOrdCarregCabecPesagem(), itemPesagemBean.getProdItemPesagem());
    }

    public ArrayList<OrdCarregBean> produtoList(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        return ordCarregDAO.ordCarregList(cabecPesagemDAO.getCabecPesagemApont().getIdOrdCarregCabecPesagem());
    }

    public ArrayList<OrdCarregBean> ordCarregArrayList(){
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        OrdCarregDAO ordCarregDAO = new OrdCarregDAO();
        return ordCarregDAO.ordCarregArrayList(cabecPesagemDAO.getCabecPesagemCriado().getPlacaVeicCabecPesagem());
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

                JSONObject objCabPesagem = cabecJsonArray.getJSONObject(i);
                Gson gsonBol = new Gson();
                CabecPesagemBean cabecPesagemBean = gsonBol.fromJson(objCabPesagem.toString(), CabecPesagemBean.class);

                ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
                itemPesagemDAO.delItemCabec(cabecPesagemBean.getIdCabecPesagem());

                CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
                cabecPesagemDAO.delCabec(cabecPesagemBean.getIdCabecPesagem());

            }

        }
        catch(Exception e){
            EnvioDadosServ.getInstance().setEnviando(false);
        }

    }

    public void deleteCabecApont() {

        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        CabecPesagemBean cabecPesagemBean = cabecPesagemDAO.getCabecPesagemApont();

        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        itemPesagemDAO.delItemCabec(cabecPesagemBean.getIdCabecPesagem());

        cabecPesagemDAO.delCabec(cabecPesagemBean.getIdCabecPesagem());

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// ENVIO DADOS SERVIDOR ///////////////////////////////////////

    public String dadosCabecFechEnvio() {
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        CabecPesagemBean cabecPesagemBean = cabecPesagemDAO.getCabecPesagemFechado();
        JsonArray jsonArrayCabec = new JsonArray();
        Gson gsonCabec = new Gson();
        jsonArrayCabec.add(gsonCabec.toJsonTree(cabecPesagemBean, cabecPesagemBean.getClass()));
        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);
        return jsonCabec.toString();
    }

    public String dadosItemFechEnvio() {
        CabecPesagemDAO cabecPesagemDAO = new CabecPesagemDAO();
        ItemPesagemDAO itemPesagemDAO = new ItemPesagemDAO();
        List itemAbordList = itemPesagemDAO.getListItem(cabecPesagemDAO.getCabecPesagemFechado().getIdCabecPesagem());

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
