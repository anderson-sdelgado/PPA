package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.CabPesagemBean;
import br.com.usinasantafe.ppa.util.Tempo;

public class CabPesagemDAO {

    public CabPesagemDAO() {
    }

    public boolean verCabecPesAberto(){
        List cabPesagemList = cabPesagAbertList();
        boolean ret = cabPesagemList.size() > 0;
        cabPesagemList.clear();
        return ret;
    }

    public void criarCabPesagem(String placaVeicCabPes, Long idEquip, Long matricFunc, Long statusCon){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        cabPesagemBean.setPlacaVeicCabPes(placaVeicCabPes);
        cabPesagemBean.setIdEquipCabPes(idEquip);
        cabPesagemBean.setMatricFuncCabPes(matricFunc);
        cabPesagemBean.setStatusConCabPes(statusCon);
        cabPesagemBean.setDthrInicialCabPes(Tempo.getInstance().dataComHora());
        cabPesagemBean.setStatusCabPes(1L);
        cabPesagemBean.setStatusApontCabPes(0L);
        cabPesagemBean.insert();
    }

    public CabPesagemBean getCabPesApont(){
        List cabPesagemList = cabPesagApontList();
        CabPesagemBean cabPesagemBean = (CabPesagemBean) cabPesagemList.get(0);
        cabPesagemList.clear();
        return cabPesagemBean;
    }

    public List<CabPesagemBean> cabPesagApontList(){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        return cabPesagemBean.get("statusApontCabPes",1L);
    }

    public List<CabPesagemBean> cabPesagAbertList(){
        CabPesagemBean cabPesagemBean = new CabPesagemBean();
        return cabPesagemBean.get("statusCabPes",1L);
    }

    public void fecharCabPesagem(){
        CabPesagemBean cabPesagemBean = getCabPesApont();
        cabPesagemBean.setDthrFinalCabPes(Tempo.getInstance().dataComHora());
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
        if(cabPesagemBean.getStatusConCabPes() == 1L){
            return true;
        }
        else{
            return false;
        }
    }

    public void setStatusApontCabPes(CabPesagemBean cabPesagemBean){
        List<CabPesagemBean> cabPesagemList = cabPesagAbertList();
        for(CabPesagemBean cabPesagemBD : cabPesagemList){
            if(cabPesagemBean.getIdCabPes().equals(cabPesagemBD.getIdCabPes())){
                cabPesagemBD.setStatusApontCabPes(1L);
                cabPesagemBD.update();
            }
            else{
                cabPesagemBD.setStatusApontCabPes(0L);
                cabPesagemBD.update();
            }
        }
    }


}
