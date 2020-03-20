package br.com.usinasantafe.ppa.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.ppa.DigOSActivity;
import br.com.usinasantafe.ppa.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ppa.util.VerifDadosServ;

public class OSDAO {

    public OSDAO() {
    }

    public void verDados(String dado, DigOSActivity digOSActivity){
        VerifDadosServ.getInstance().verDados(dado, "OS", digOSActivity);
    }

    public void recDados(String result, DigOSActivity digOSActivity){

        try {

            digOSActivity.setVerDados(false);

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        OSBean osBean = gson.fromJson(objeto.toString(), OSBean.class);
                        osBean.insert();

                    }

                    digOSActivity.avancaSucesso();

                } else {
                    digOSActivity.msg("OS INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
                }

            } else {
                digOSActivity.msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            digOSActivity.msg("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    public boolean verBD(Long nroOS){
        OSBean osBean = new OSBean();
        if(!osBean.hasElements()){
            return false;
        }
        else{
            List equipList = osBean.get("nroOS", nroOS);
            boolean retorno = equipList.size() > 0;
            equipList.clear();
            return retorno;
        }
    }

}
