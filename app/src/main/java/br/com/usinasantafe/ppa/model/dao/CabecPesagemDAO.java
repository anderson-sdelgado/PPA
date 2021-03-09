package br.com.usinasantafe.ppa.model.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.CabecPesagemBean;
import br.com.usinasantafe.ppa.model.pst.EspecificaPesquisa;
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
        cabecPesagemBean.setIdOrdCarregCabecPesagem(0L);
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
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecCriado());
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        return cabecPesagemBean.get(pesqArrayList);
    }

    public List<CabecPesagemBean> cabecPesagemApontList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecAberto());
        pesqArrayList.add(getPesqCabecApont());
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        return cabecPesagemBean.get(pesqArrayList);
    }

    public List<CabecPesagemBean> cabecPesagemAbertList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecAberto());
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        return cabecPesagemBean.get(pesqArrayList);
    }

    public void fecharCabecPesagem(){
        CabecPesagemBean cabecPesagemBean = getCabecPesagemApont();
        cabecPesagemBean.setDthrFinalCabecPesagem(Tempo.getInstance().dataComHora());
        cabecPesagemBean.setStatusCabecPesagem(2L);
        cabecPesagemBean.update();
    }

    public CabecPesagemBean getCabecPesagemFechado(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecFechado());
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        List<CabecPesagemBean> cabecPesagemList = cabecPesagemBean.get(pesqArrayList);
        cabecPesagemBean = cabecPesagemList.get(0);
        cabecPesagemList.clear();
        return cabecPesagemBean;
    }

    public boolean verCabecPesagemFechado(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecFechado());
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        List<CabecPesagemBean> cabecPesagemList = cabecPesagemBean.get(pesqArrayList);
        boolean retorno = cabecPesagemList.size() > 0;
        cabecPesagemList.clear();
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
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecAberto());
        CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
        List<CabecPesagemBean> cabecPesagemList = cabecPesagemBean.get(pesqArrayList);
        cabecPesagemBean = (CabecPesagemBean) cabecPesagemList.get(0);
        cabecPesagemList.clear();
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

    private EspecificaPesquisa getPesqCabecCriado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabecPesagem");
        pesquisa.setValor(0L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabecPesagem");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabecPesagem");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecApont(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontCabecPesagem");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
