package br.com.usinasantafe.ppa.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.dao.CabAbordDAO;
import br.com.usinasantafe.pst.bean.dao.FotoAbordDAO;
import br.com.usinasantafe.pst.bean.dao.ItemAbordDAO;
import br.com.usinasantafe.pst.bean.variaveis.CabAbordBean;
import br.com.usinasantafe.pst.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.bean.variaveis.ItemAbordBean;
import br.com.usinasantafe.pst.util.AtualDadosServ;
import br.com.usinasantafe.pst.util.EnvioDadosServ;

public class AbordagemCTR {

    private CabAbordBean cabAbordBean;

    public AbordagemCTR() {
        if (cabAbordBean == null)
            cabAbordBean = new CabAbordBean();
    }

    public void setMatricFuncObsForm(Long matricFuncObsForm) {
        this.cabAbordBean.setMatricFuncCabAbord(matricFuncObsForm);
    }

    public Long getMatricFuncObsForm(){
        return this.cabAbordBean.getMatricFuncCabAbord();
    }

    public void setIdAreaForm(Long idAreaForm){
        this.cabAbordBean.setIdAreaCabAbord(idAreaForm);
    }

    public Long getIdAreaForm(){
        return this.cabAbordBean.getIdAreaCabAbord();
    }

    public void setIdSubAreaForm(Long idSubAreaForm){
        this.cabAbordBean.setIdSubAreaCabAbord(idSubAreaForm);
    }

    public void setIdTurno(Long idTurno){
        this.cabAbordBean.setIdTurnoCabAbord(idTurno);
    }

    public void setDetalhesCabAbord(Long extensaoMinCabAbord, Long pessoasContCabAbord, Long pessoasObsCabAbord){
        this.cabAbordBean.setExtensaoMinCabAbord(extensaoMinCabAbord);
        this.cabAbordBean.setPessoasContCabAbord(pessoasContCabAbord);
        this.cabAbordBean.setPessoasObsCabAbord(pessoasObsCabAbord);
    }

    public void setComentarioCabAbord(Long tipoCabAbord, String comentarioCabAbord){
        this.cabAbordBean.setTipoCabAbord(tipoCabAbord);
        this.cabAbordBean.setComentCabAbord(comentarioCabAbord);
    }

    public boolean verEnvioDados() {
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        return cabAbordDAO.cabecFechList().size() > 0;
    }

    public void salvarCabecAberto(){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        cabAbordDAO.salvarCabecAbert(cabAbordBean);
    }

    public void salvarItem(Long idQuestao, Long seguro, Long inseguro){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        ItemAbordBean itemAbordBean = new ItemAbordBean();
        itemAbordBean.setIdCabItemAbord(cabAbordBean.getIdCabAbord());
        itemAbordBean.setIdQuestaoItemAbord(idQuestao);
        itemAbordBean.setQtdeSegItemAbord(seguro);
        itemAbordBean.setQtdeInsegItemAbord(inseguro);
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        itemAbordDAO.salvarItem(itemAbordBean);
    }

    public boolean verItemCabecAbert(Long idQuestao){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        return itemAbordDAO.verItemCabecAbert(cabAbordBean.getIdCabAbord(), idQuestao);
    }

    public ItemAbordBean getItemCabecAbert(Long idQuestao){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        return itemAbordDAO.getItemCabecAberts(cabAbordBean.getIdCabAbord(), idQuestao);
    }

    public FotoAbordBean salvarFoto(Bitmap bitmap){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.salvarFoto(cabAbordBean.getIdCabAbord(), bitmap);
    }

    public List getListFotoCabecAbert(){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.getListFotoCabecAbert(cabAbordBean.getIdCabAbord());
    }

    public void salvaBolFechado(){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        cabAbordDAO.salvarCabecFech(cabAbordBean);
    }

    public String dadosCabecFechEnvio() {
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = (CabAbordBean) cabAbordDAO.cabecFechList().get(0);
        JsonArray jsonArrayCabec = new JsonArray();
        Gson gsonCabec = new Gson();
        jsonArrayCabec.add(gsonCabec.toJsonTree(cabAbordBean, cabAbordBean.getClass()));
        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);
        return jsonCabec.toString();
    }

    public String dadosItemFechEnvio() {
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        CabAbordBean cabAbordBean = (CabAbordBean) cabAbordDAO.cabecFechList().get(0);
        List itemAbordList = itemAbordDAO.getListItemCabecFech(cabAbordBean.getIdCabAbord());

        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < itemAbordList.size(); i++) {
            ItemAbordBean itemAbordBean = (ItemAbordBean) itemAbordList.get(i);
            Gson gsonItem = new Gson();
            jsonArrayItem.add(gsonItem.toJsonTree(itemAbordBean, itemAbordBean.getClass()));
        }

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);
        return jsonItem.toString();
    }

    public String dadosFotoFechEnvio(int pos){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = (CabAbordBean) cabAbordDAO.cabecFechList().get(0);
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        List fotoAbordList = fotoAbordDAO.getListFotoCabecAbert(cabAbordBean.getIdCabAbord());

        JsonArray jsonArrayFoto = new JsonArray();

        if(fotoAbordList.size() >= pos) {
            FotoAbordBean fotoAbordBean = (FotoAbordBean) fotoAbordList.get(pos - 1);
            Gson gsonFoto = new Gson();
            jsonArrayFoto.add(gsonFoto.toJsonTree(fotoAbordBean, fotoAbordBean.getClass()));
        }

        JsonObject jsonFoto = new JsonObject();
        jsonFoto.add("foto", jsonArrayFoto);
        return jsonFoto.toString();

    }

    public void deleteCabec(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            Long idCabec = Long.valueOf(retorno.substring(pos1));

            CabAbordDAO cabAbordDAO = new CabAbordDAO();
            cabAbordDAO.delCabec(idCabec);

            ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
            itemAbordDAO.delItemCabec(idCabec);

            FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
            fotoAbordDAO.delFotoCabec(idCabec);

            EnvioDadosServ.getInstance().envioDados();

        }
        catch(Exception e){
            EnvioDadosServ.getInstance().setEnviando(false);
        }

    }

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosArea(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("AreaBean");
        arrayList.add("SubAreaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosSubArea(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("SubAreaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosItem(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("TipoBean");
        arrayList.add("TopicoBean");
        arrayList.add("QuestaoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void clearBD(){

        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        List cabAbordList = cabAbordDAO.cabecAbertList();

        if(cabAbordList.size() > 0){

            CabAbordBean cabAbordBean = (CabAbordBean) cabAbordList.get(0);

            ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
            itemAbordDAO.delItemCabec(cabAbordBean.getIdCabAbord());

            FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
            fotoAbordDAO.delFotoCabec(cabAbordBean.getIdCabAbord());

            cabAbordBean.delete();

        }

    }

    public Bitmap getStringBitmap(String foto){
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.getStringBitmap(foto);
    }

}
