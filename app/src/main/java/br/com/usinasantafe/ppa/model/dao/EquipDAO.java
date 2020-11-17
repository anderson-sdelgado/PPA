package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.estaticas.EquipBean;


public class EquipDAO {

    public EquipDAO() {
    }

    public EquipBean getIdEquip(Long idEquip){
        List<EquipBean> equipList = idEquipList(idEquip);
        EquipBean equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public EquipBean getNroEquip(Long nroEquip){
        List<EquipBean> equipList = nroEquipList(nroEquip);
        EquipBean equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public boolean verNroEquip(Long nroEquip){
        List<EquipBean> equipList = nroEquipList(nroEquip);
        boolean ret = equipList.size() > 0;
        equipList.clear();
        return ret;
    }

    public List<EquipBean> nroEquipList(Long nroEquip){
        EquipBean equipBean = new EquipBean();
        return equipBean.get("nroEquip", nroEquip);
    }

    public List<EquipBean> idEquipList(Long idEquip){
        EquipBean equipBean = new EquipBean();
        return equipBean.get("idEquip", idEquip);
    }

}
