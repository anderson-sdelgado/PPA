package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.bean.db.Entidade;

@DatabaseTable(tableName="tbcabpesvar")
public class CabPesagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabPes;
    @DatabaseField
    private Long matricFuncCabPes;
    @DatabaseField
    private String placaVeicCabPes;
    @DatabaseField
    private String fotoCabPes;
    @DatabaseField
    private String dthrCabPes;
    @DatabaseField
    private Long statusConVeicCabPes;  //0 - OffLine; 1 - OnLine
    @DatabaseField
    private Long statusCabPes; //1 - Aberto; 2 - Fechado;


    public CabPesagemBean() {
    }

    public Long getIdCabPes() {
        return idCabPes;
    }

    public void setIdCabPes(Long idCabPes) {
        this.idCabPes = idCabPes;
    }

    public String getPlacaVeicCabPes() {
        return placaVeicCabPes;
    }

    public void setPlacaVeicCabPes(String placaVeicCabPes) {
        this.placaVeicCabPes = placaVeicCabPes;
    }

    public String getFotoCabPes() {
        return fotoCabPes;
    }

    public void setFotoCabPes(String fotoCabPes) {
        this.fotoCabPes = fotoCabPes;
    }

    public String getDthrCabPes() {
        return dthrCabPes;
    }

    public void setDthrCabPes(String dthrCabPes) {
        this.dthrCabPes = dthrCabPes;
    }

    public Long getStatusCabPes() {
        return statusCabPes;
    }

    public void setStatusCabPes(Long statusCabPes) {
        this.statusCabPes = statusCabPes;
    }

    public Long getMatricFuncCabPes() {
        return matricFuncCabPes;
    }

    public void setMatricFuncCabPes(Long matricFuncCabPes) {
        this.matricFuncCabPes = matricFuncCabPes;
    }

    public Long getStatusConVeicCabPes() {
        return statusConVeicCabPes;
    }

    public void setStatusConVeicCabPes(Long statusConVeicCabPes) {
        this.statusConVeicCabPes = statusConVeicCabPes;
    }

}
