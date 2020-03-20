package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.CabPesagemBean;
import br.com.usinasantafe.ppa.util.Tempo;

public class CabPesagemDAO {

    public CabPesagemDAO() {
    }


    public boolean verCabecPesAberto(){
        List cabPesagemList = cabPesAbertoList();
        boolean ret = cabPesagemList.size() > 0;
        cabPesagemList.clear();
        return ret;
    }

    public void criarCabPesagem(String placaVeicCabPes, Long matricFunc, Long statusCon){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        cabPesagemBean.setPlacaVeicCabPes(placaVeicCabPes);
        cabPesagemBean.setMatricFuncCabPes(matricFunc);
        cabPesagemBean.setStatusConVeicCabPes(statusCon);
        cabPesagemBean.setStatusCabPes(1L);
        cabPesagemBean.insert();
    }

    public CabPesagemBean getCabPesAberto(){
        List cabPesagemList = cabPesAbertoList();
        CabPesagemBean cabPesagemBean = (CabPesagemBean) cabPesagemList.get(0);
        cabPesagemList.clear();
        return cabPesagemBean;
    }

    private List cabPesAbertoList(){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        return cabPesagemBean.get("statusCabPes",1L);
    }

    public void fechCabPesagem(String imagem){
        CabPesagemBean cabPesagemBean = getCabPesAberto();
        cabPesagemBean.setFotoCabPes(imagem);
        cabPesagemBean.setDthrCabPes(Tempo.getInstance().dataComHora());
        cabPesagemBean.setStatusCabPes(2L);
        cabPesagemBean.update();
    }

    public CabPesagemBean getCabPesFechado(){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        List cabPesagemList = cabPesagemBean.get("statusCabPes",2L);
        cabPesagemBean = (CabPesagemBean) cabPesagemList.get(0);
        cabPesagemList.clear();
        return cabPesagemBean;
    }

    public boolean verCabPesFechado(){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        List cabPesagemList = cabPesagemBean.get("statusCabPes",2L);
        boolean retorno = cabPesagemList.size() > 0;
        cabPesagemList.clear();
        return retorno;
    }

    public void delCabec(Long idCabAbord) {
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        List cabecList = cabPesagemBean.get("idCabPes", idCabAbord);
        cabPesagemBean = (CabPesagemBean) cabecList.get(0);
        cabPesagemBean.delete();
        cabecList.clear();
    }

    public boolean verStatusConPlacaVeic(){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        List cabPesagemList = cabPesagemBean.get("statusCabPes",1L);
        cabPesagemBean = (CabPesagemBean) cabPesagemList.get(0);
        cabPesagemList.clear();
        if(cabPesagemBean.getStatusConVeicCabPes() == 1L){
            return true;
        }
        else{
            return false;
        }
    }

    public Long getStatusConVeicCabPes(){
        return getCabPesAberto().getStatusConVeicCabPes();
    }

}
