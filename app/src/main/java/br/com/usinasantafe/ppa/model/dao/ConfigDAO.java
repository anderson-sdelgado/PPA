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
        configBean.setVeiculoConfig(0L);
        configBean.setNotaFiscalConfig(0L);
        configBean.setItemNFConfig(0L);
        configBean.setOsConfig(0L);
        configBean.deleteAll();
        configBean.insert();
    }

    public void setVeiculoConfig(Long veiculoConfig){
        ConfigBean configBean = getConfig();
        configBean.setVeiculoConfig(veiculoConfig);
        configBean.update();
    }

    public void setNotaFiscalConfig(Long notaFiscalConfig){
        ConfigBean configBean = getConfig();
        configBean.setNotaFiscalConfig(notaFiscalConfig);
        configBean.update();
    }

    public void setItemNFConfig(Long itemNFConfig){
        ConfigBean configBean = getConfig();
        configBean.setItemNFConfig(itemNFConfig);
        configBean.update();
    }

    public void setOSConfig(Long osConfig){
        ConfigBean configBean = getConfig();
        configBean.setOsConfig(osConfig);;
        configBean.update();
    }

}
