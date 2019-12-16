package br.com.usinasantafe.ppa.model.bean.variaveis;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualAplicBean {

    private String versaoAtual;
    private String versaoNova;

    public AtualAplicBean() {
    }

    public String getVersaoAtual() {
        return versaoAtual;
    }

    public void setVersaoAtual(String versaoAtual) {
        this.versaoAtual = versaoAtual;
    }

    public String getVersaoNova() {
        return versaoNova;
    }

    public void setVersaoNova(String versaoNova) {
        this.versaoNova = versaoNova;
    }
}
