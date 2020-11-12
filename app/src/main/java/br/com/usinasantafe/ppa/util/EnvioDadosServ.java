package br.com.usinasantafe.ppa.util;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.ppa.control.PesagemCTR;
import br.com.usinasantafe.ppa.util.conHttp.PostMultipartGenerico;
import br.com.usinasantafe.ppa.util.conHttp.UrlsConexaoHttp;


public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    /////////////////////////////////// ENVIAR DADOS //////////////////////////////////////////////

    public void dadosEnvio() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
        PesagemCTR pesagemCTR = new PesagemCTR();

        String[] dados = new String[7];

        String cabec = pesagemCTR.dadosCabecFechEnvio();
        String item = pesagemCTR.dadosItemFechEnvio();

        Log.i("PST", "CABECALHO = " + cabec);
        Log.i("PST", "ITEM = " + item);

        dados[0] = urlsConexaoHttp.getsInserirDados();
        dados[1] = cabec;
        dados[2] = item;

        PostMultipartGenerico postMultipartGenerico = new PostMultipartGenerico();
        postMultipartGenerico.execute(dados);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// VERIFICAÇÃO DE DADOS //////////////////////////////////////

    public Boolean verifCabecFechado() {
        PesagemCTR pesagemCTR = new PesagemCTR();
        return pesagemCTR.verEnvioDados();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////

    public void envioDados(Context context) {
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            enviando = true;
            dadosEnvio();
        }
        else{
            enviando = false;
        }

    }

    public boolean verifDadosEnvio() {
        if (!verifCabecFechado()){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public boolean isEnviando() {
        return enviando;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }
}