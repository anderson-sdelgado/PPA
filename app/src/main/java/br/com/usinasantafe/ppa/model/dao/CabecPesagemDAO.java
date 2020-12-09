package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.CabecPesagemBean;
import br.com.usinasantafe.ppa.util.Tempo;

public class CabecPesagemDAO {

    public CabecPesagemDAO() {
    }

    public boolean verCabecPesAberto(){
        List cabecPesagemList = cabecPesagemAbertList();
        boolean ret = cabecPesagemList.size() > 0;
        cabecPesagemList.clear();
        return ret;
    }

    public void criarCabecPesagem(String placa, Long idEquip, Long statusCon){
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        cabecPesagemBean.setPlacaVeicCabecPesagem(placa);
        cabecPesagemBean.setIdEquipCabecPesagem(idEquip);
        cabecPesagemBean.setStatusConCabecPesagem(statusCon);
        cabecPesagemBean.setDthrInicialCabecPesagem(Tempo.getInstance().dataComHora());
        cabecPesagemBean.setStatusCabecPesagem(0L);
        cabecPesagemBean.setStatusApontCabecPesagem(0L);
        cabecPesagemBean.insert();
    }

    public void abrirCabecPesagem(Long idOrdCarreg){
        CabecPesagemBean cabecPesagemBean = getCabecPesagemCriado();
        cabecPesagemBean.setIdOrdCarregCabecPesagem(idOrdCarreg);
        cabecPesagemBean.setStatusCabecPesagem(1L);
        cabecPesagemBean.update();
    }

    public CabecPesagemBean getCabecPesagemApont(){
        List cabecPesagemList = cabecPesagemApontList();
        CabecPesagemBean cabecPesagemBean = (CabecPesagemBean) cabecPesagemList.get(0);
        cabecPesagemList.clear();
        return cabecPesagemBean;
    }

    public CabecPesagemBean getCabecPesagemCriado(){
        List cabPesagemList = cabecPesagemCriadoList();
        CabecPesagemBean cabecPesagemBean = (CabecPesagemBean) cabPesagemList.get(0);
        cabPesagemList.clear();
        return cabecPesagemBean;
    }

    public List<CabecPesagemBean> cabecPesagemCriadoList(){
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        return cabecPesagemBean.get("statusCabecPesagem",0L);
    }

    public List<CabecPesagemBean> cabecPesagemApontList(){
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        return cabecPesagemBean.get("statusApontCabecPesagem",1L);
    }

    public List<CabecPesagemBean> cabecPesagemAbertList(){
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        return cabecPesagemBean.get("statusCabecPesagem",1L);
    }

    public void fecharCabecPesagem(){
        CabecPesagemBean cabecPesagemBean = getCabecPesagemApont();
        cabecPesagemBean.setDthrFinalCabecPesagem(Tempo.getInstance().dataComHora());
        cabecPesagemBean.setStatusCabecPesagem(2L);
        cabecPesagemBean.update();
    }

    public CabecPesagemBean getCabecPesagemFechado(){
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        List cabPesagemList = cabecPesagemBean.get("statusCabecPesagem",2L);
        cabecPesagemBean = (CabecPesagemBean) cabPesagemList.get(0);
        cabPesagemList.clear();
        return cabecPesagemBean;
    }

    public boolean verCabecPesagemFechado(){
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        List cabPesagemList = cabecPesagemBean.get("statusCabecPesagem",2L);
        boolean retorno = cabPesagemList.size() > 0;
        cabPesagemList.clear();
        return retorno;
    }

    public void delCabec(Long idCabecAbord) {
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        List<CabecPesagemBean> cabecList = cabecPesagemBean.get("idCabecPesagem", idCabecAbord);
        cabecPesagemBean = (CabecPesagemBean) cabecList.get(0);
        cabecPesagemBean.delete();
        cabecList.clear();
    }

    public void delCabecCriado(){
        List<CabecPesagemBean> cabecList = cabecPesagemCriadoList();
        for(CabecPesagemBean cabecPesagemBean : cabecList){
            cabecPesagemBean.delete();
        }
        cabecList.clear();
    }

    public boolean verStatusConPlacaVeic(){
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        List cabPesagemList = cabecPesagemBean.get("statusCabecPesagem",1L);
        cabecPesagemBean = (CabecPesagemBean) cabPesagemList.get(0);
        cabPesagemList.clear();
        if(cabecPesagemBean.getStatusConCabecPesagem() == 1L){
            return true;
        }
        else{
            return false;
        }
    }

    public void setStatusApontCabecPesagem(CabecPesagemBean cabecPesagemBean){
        List<CabecPesagemBean> cabPesagemList = cabecPesagemAbertList();
        for(CabecPesagemBean cabPesagemBD : cabPesagemList){
            if(cabecPesagemBean.getIdCabecPesagem().equals(cabPesagemBD.getIdCabecPesagem())){
                cabPesagemBD.setStatusApontCabecPesagem(1L);
                cabPesagemBD.update();
            }
            else{
                cabPesagemBD.setStatusApontCabecPesagem(0L);
                cabPesagemBD.update();
            }
        }
    }

}
