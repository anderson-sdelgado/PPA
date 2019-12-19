package br.com.usinasantafe.ppa.control;

import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.model.dao.ConfigDAO;
import br.com.usinasantafe.ppa.model.dao.FuncDAO;

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

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    public void insConfig(Long matricFunc, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.insConfig(matricFunc, senha);
    }

    public void setVeiculoConfig(Long veiculoConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setVeiculoConfig(veiculoConfig);
    }

    public void setNotaFiscalConfig(Long notaFiscalConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setNotaFiscalConfig(notaFiscalConfig);
    }

    public void setItemNFConfig(Long itemNFConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setItemNFConfig(itemNFConfig);
    }

    public void setOSConfig(Long osConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setOSConfig(osConfig);
    }

}
