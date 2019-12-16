package br.com.usinasantafe.ppa.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.ppa.MenuInicialActivity;
import br.com.usinasantafe.ppa.model.bean.db.GenericRecordable;
import br.com.usinasantafe.ppa.model.bean.variaveis.AtualAplicBean;


/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx1;
    private Class telaProx2;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private AtualAplicBean atualAplicBean;
    private MenuInicialActivity menuInicialActivity;
    private ConHttpPostVerGenerico conHttpPostVerGenerico;
    private boolean verTerm;
    private String senha;
    private int verTelaAtualPerda = 0;

    public VerifDadosServ() {
        //genericRecordable = new GenericRecordable();
    }

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

        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setVersaoAtual(versaoAplic);

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        conHttpPostVerGenerico = new ConHttpPostVerGenerico();
        conHttpPostVerGenerico.setParametrosPost(parametrosPost);
        conHttpPostVerGenerico.execute(url);

    }

}
