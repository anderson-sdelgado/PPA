package br.com.usinasantafe.ppa.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.ppa.view.DigPlacaVeicActivity;
import br.com.usinasantafe.ppa.model.bean.estaticas.VeiculoBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.ItemNFBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.NotaFiscalBean;
import br.com.usinasantafe.ppa.util.VerifDadosServ;

public class VeiculoDAO {

    public VeiculoDAO() {
    }

    public void verDados(String dado, DigPlacaVeicActivity digPlacaVeicActivity){
        VerifDadosServ.getInstance().verDados(dado, "Veiculo", digPlacaVeicActivity);
    }

    public void recDados(String result, DigPlacaVeicActivity digPlacaVeicActivity){

        try {

            digPlacaVeicActivity.setVerDados(false);

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("#") + 1;
                int pos2 = result.indexOf("|") + 1;
                String objPrim = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1, (pos2 - 1));
                String objTerc = result.substring(pos2);

                JSONObject jObj = new JSONObject(objPrim);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        VeiculoBean veiculoBean = gson.fromJson(objeto.toString(), VeiculoBean.class);
                        veiculoBean.insert();

                    }

                    jObj = new JSONObject(objSeg);
                    jsonArray = jObj.getJSONArray("dados");

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        NotaFiscalBean notaFiscalBean = gson.fromJson(objeto.toString(), NotaFiscalBean.class);
                        notaFiscalBean.insert();

                    }

                    jObj = new JSONObject(objTerc);
                    jsonArray = jObj.getJSONArray("dados");

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        ItemNFBean itemNFBean = gson.fromJson(objeto.toString(), ItemNFBean.class);
                        itemNFBean.insert();

                    }

                    digPlacaVeicActivity.avancaSucesso();

                } else {
                    digPlacaVeicActivity.msg("VEÍCULO INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO DA PLAC.");
                }

            } else {
                digPlacaVeicActivity.msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            digPlacaVeicActivity.msg("FALHA DE PESQUISA DE VEÍCULO! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    public boolean verBD(String placa){
        VeiculoBean veiculoBean = new VeiculoBean();
        if(!veiculoBean.hasElements()){
            return false;
        }
        else{
            List equipList = veiculoBean.get("placaVeiculo", placa);
            boolean retorno = equipList.size() > 0;
            equipList.clear();
            return retorno;
        }
    }

}
