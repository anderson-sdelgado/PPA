package br.com.usinasantafe.ppa.util;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.ppa.view.DigOSActivity;
import br.com.usinasantafe.ppa.view.DigPlacaVeicActivity;
import br.com.usinasantafe.ppa.view.MenuInicialActivity;
import br.com.usinasantafe.ppa.control.ConfigCTR;
import br.com.usinasantafe.ppa.model.bean.variaveis.AtualAplicBean;
import br.com.usinasantafe.ppa.model.dao.OSDAO;
import br.com.usinasantafe.ppa.model.dao.VeiculoDAO;
import br.com.usinasantafe.ppa.util.conHttp.PostVerGenerico;
import br.com.usinasantafe.ppa.util.conHttp.UrlsConexaoHttp;


/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private MenuInicialActivity menuInicialActivity;
    private DigPlacaVeicActivity digPlacaVeicActivity;
    private DigOSActivity digOSActivity;
    private PostVerGenerico postVerGenerico;

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {
        try {

            if (!result.equals("")) {
                if (this.tipo.equals("Atualiza")) {
                    String verAtualizacao = result.trim();
                    if (verAtualizacao.equals("S")) {
                        AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                        atualizarAplicativo.setContext(this.menuInicialActivity);
                        atualizarAplicativo.execute();
                    } else {
                        this.menuInicialActivity.startTimer();
                    }
                } else if (this.tipo.equals("Veiculo")) {
                    VeiculoDAO veiculoDAO = new VeiculoDAO();
                    veiculoDAO.recDados(result, digPlacaVeicActivity);
                } else if (this.tipo.equals("OS")) {
                    OSDAO osdao = new OSDAO();
                    osdao.recDados(result, digOSActivity);
                }
            }

        } catch (Exception e) {
            Log.i("PMM", "Erro Manip atualizar = " + e);
        }

    }

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

        ConfigCTR configCTR = new ConfigCTR();
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setVersaoAtual(versaoAplic);
        atualAplicBean.setMatricFunc(String.valueOf(configCTR.getConfig().getMatricFuncConfig()));

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void verDados(String dado, String tipo, DigPlacaVeicActivity digPlacaVeicActivity) {

        this.dado = dado;
        this.tipo = tipo;
        this.digPlacaVeicActivity = digPlacaVeicActivity;

        envioDados();

    }

    public void verDados(String dado, String tipo, DigOSActivity digOSActivity) {

        this.dado = dado;
        this.tipo = tipo;
        this.digOSActivity = digOSActivity;

        envioDados();

    }

    public void envioDados() {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", String.valueOf(dado));

        Log.i("PMM", "VERIFICA = " + String.valueOf(dado));

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void cancelVer() {
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

}
