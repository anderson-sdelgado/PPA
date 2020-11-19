package br.com.usinasantafe.ppa.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppa.control.PesagemCTR;
import br.com.usinasantafe.ppa.model.bean.estaticas.OrdCarregBean;
import br.com.usinasantafe.ppa.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.ppa.util.VerifDadosServ;

public class OrdCarregDAO {

    public OrdCarregDAO() {
    }

    public void verDados(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verDados(dado, "OrdCarreg", telaAtual, telaProx, progressDialog);
    }

    public void recDados(String result, String placa){

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    OrdCarregBean ordCarregBean = new OrdCarregBean();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        ordCarregBean = gson.fromJson(objeto.toString(), OrdCarregBean.class);
                        ordCarregBean.insert();

                    }

                    PesagemCTR pesagemCTR = new PesagemCTR();
                    pesagemCTR.criarCabecPes(ordCarregBean.getPlacaVeicOrdCarreg(), 1L);

                    VerifDadosServ.getInstance().pulaTelaComTerm();

                } else {

                    VerifDadosServ.getInstance().msgComTerm("VEÍCULO INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO DA PLACA.");

                }

            } else {

                VerifDadosServ.getInstance().msgComTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");

            }

        } catch (Exception e) {

            VerifDadosServ.getInstance().msgComTerm("FALHA DE PESQUISA DE VEÍCULO! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");

        }

    }

    public boolean verOrdCarreg(String placa){
        List<OrdCarregBean> ordCarregList = ordCarregList(placa);
        boolean ret = (ordCarregList.size() > 0);
        ordCarregList.clear();
        return ret;
    }

    public void deleteDataDif(String data){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDataDif(data));
        OrdCarregBean ordCarregBean = new OrdCarregBean();
        List<OrdCarregBean> ordCarregList = ordCarregBean.get(pesqArrayList);
        for(OrdCarregBean ordCarregBD : ordCarregList){
            ordCarregBD.delete();
        }
        ordCarregList.clear();
    }

    public OrdCarregBean getOrdCarregProd(String codProd){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCodProd(codProd));
        OrdCarregBean ordCarregBean = new OrdCarregBean();
        List<OrdCarregBean> ordCarregList = ordCarregBean.get(pesqArrayList);
        ordCarregBean = ordCarregList.get(0);
        pesqArrayList.clear();
        ordCarregList.clear();
        return ordCarregBean;
    }

    public List<OrdCarregBean> ordCarregList(String placa){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqPlaca(placa));
        OrdCarregBean ordCarregBean = new OrdCarregBean();
        return ordCarregBean.get(pesqArrayList);
    }

    public List<OrdCarregBean> ordCarregList(String placa, Long nroOS){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqPlaca(placa));
        pesqArrayList.add(getPesqNroOS(nroOS));
        OrdCarregBean ordCarregBean = new OrdCarregBean();
        return ordCarregBean.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqDataDif(String data){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dataOrdCarreg");
        pesquisa.setValor(data);
        pesquisa.setTipo(2);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqPlaca(String placa){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("placaVeicOrdCarreg");
        pesquisa.setValor(placa);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqNroOS(Long nroOS){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("nroOSOrdCarreg");
        pesquisa.setValor(nroOS);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCodProd(String codProd){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codProdOrdCarreg");
        pesquisa.setValor(codProd);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
