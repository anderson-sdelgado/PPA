package br.com.usinasantafe.ppa.model.dao;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;

public class FuncDAO {

    public boolean verFunc(Long matricFunc){
        FuncBean funcBean = new FuncBean();
        List funcList = funcBean.get("matricFunc", matricFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

}
