package br.com.usinasantafe.ppa.model.bean;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualAplicBean {

    private String versaoAtual;
    private String versaoNova;
    private String idEquip;

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

    public String getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(String idEquip) {
        this.idEquip = idEquip;
    }
}
