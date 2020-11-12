package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public boolean getConfigSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.all();
        configBean = (ConfigBean) configList.get(0);
        configList.clear();
        return configBean;
    }

    public void insConfig(Long idEquip, String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.setIdEquipConfig(idEquip);
        configBean.setSenhaConfig(senha);
        configBean.setDataClearConfig("");
        configBean.deleteAll();
        configBean.insert();
    }

    public void setDataClearConfig(String dataClearConfig){
        ConfigBean configBean = getConfig();
        configBean.setDataClearConfig(dataClearConfig);
        configBean.update();
    }

    public void setMatricFuncConfig(Long matricFunc){
        ConfigBean configBean = getConfig();
        configBean.setMatricFuncConfig(matricFunc);
        configBean.update();
    }

}
