package br.com.usinasantafe.ppa.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.ppa.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.model.dao.ConfigDAO;
import br.com.usinasantafe.ppa.model.dao.EquipDAO;
import br.com.usinasantafe.ppa.model.dao.FuncDAO;
import br.com.usinasantafe.ppa.util.AtualDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public boolean getConfigSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfigSenha(senha);
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public EquipBean getEquip(Long nroEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getNroEquip(nroEquip);
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getIdEquip(getConfig().getIdEquipConfig());
    }

    public boolean verEquip(Long nroEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.verNroEquip(nroEquip);
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    public void insConfig(Long nroEquip, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.insConfig(getEquip(nroEquip).getIdEquip(), senha);
    }

    public FuncBean getFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(getConfig().getMatricFuncConfig());
    }

    public FuncBean getFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(matricFunc);
    }

    public void setFuncConfig(Long matricFunc){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setMatricFuncConfig(matricFunc);
    }

    public void atualTodasTabelas(Context context, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabelas(progressDialog, context);
    }

}
