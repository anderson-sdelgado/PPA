package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;

public class FuncDAO {

    public boolean hasElements(){
        FuncBean funcBean = new FuncBean();
        return funcBean.hasElements();
    }

    public FuncBean getFunc(Long matricFunc){
        List<FuncBean> funcList = funcList(matricFunc);
        FuncBean funcBean = funcList.get(0);
        funcList.clear();
        return funcBean;
    }

    public boolean verFunc(Long matricFunc){
        List<FuncBean> funcList = funcList(matricFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

    public List<FuncBean> funcList(Long matricFunc){
        FuncBean funcBean = new FuncBean();
        return funcBean.get("matricFunc", matricFunc);
    }

}
