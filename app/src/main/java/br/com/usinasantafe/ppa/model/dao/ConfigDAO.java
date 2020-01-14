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

    public void insConfig(Long matricFunc, String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.setMatricFuncConfig(matricFunc);
        configBean.setSenhaConfig(senha);
        configBean.setNotaFiscalConfig(0L);
        configBean.setItemNFConfig("");
        configBean.setNroOSConfig(0L);
        configBean.deleteAll();
        configBean.insert();
    }

    public void setNotaFiscalConfig(Long notaFiscalConfig){
        ConfigBean configBean = getConfig();
        configBean.setNotaFiscalConfig(notaFiscalConfig);
        configBean.update();
    }

    public void setItemNFConfig(String itemNFConfig){
        ConfigBean configBean = getConfig();
        configBean.setItemNFConfig(itemNFConfig);
        configBean.update();
    }

    public void setNroOSConfig(Long nroOSConfig, Long statusCon){
        ConfigBean configBean = getConfig();
        configBean.setNroOSConfig(nroOSConfig);
        configBean.setStatusConOSConfig(statusCon);
        configBean.update();
    }

}
