package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.pst.Entidade;

@DatabaseTable(tableName="tbitempesvar")
public class ItemPesagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemPes;
    @DatabaseField
    private Long idCabItemPes;
    @DatabaseField
    private Long matricFuncItemPes;
    @DatabaseField
    private String prodItemPes;
    @DatabaseField
    private Long nroOSItemPes;
    @DatabaseField
    private Double pesoItemPes;
    @DatabaseField
    private String comentFalhaItemPes;
    @DatabaseField
    private String dthrItemPes;
    @DatabaseField
    private Double latitudeItemPes;
    @DatabaseField
    private Double longitudeItemPes;

    public ItemPesagemBean() {
    }

    public Long getIdItemPes() {
        return idItemPes;
    }

    public void setIdItemPes(Long idItemPes) {
        this.idItemPes = idItemPes;
    }

    public Long getIdCabItemPes() {
        return idCabItemPes;
    }

    public void setIdCabItemPes(Long idCabItemPes) {
        this.idCabItemPes = idCabItemPes;
    }

    public Long getMatricFuncItemPes() {
        return matricFuncItemPes;
    }

    public void setMatricFuncItemPes(Long matricFuncItemPes) {
        this.matricFuncItemPes = matricFuncItemPes;
    }

    public String getProdItemPes() {
        return prodItemPes;
    }

    public void setProdItemPes(String prodItemPes) {
        this.prodItemPes = prodItemPes;
    }

    public Double getPesoItemPes() {
        return pesoItemPes;
    }

    public void setPesoItemPes(Double pesoItemPes) {
        this.pesoItemPes = pesoItemPes;
    }

    public String getComentFalhaItemPes() {
        return comentFalhaItemPes;
    }

    public void setComentFalhaItemPes(String comentFalhaItemPes) {
        this.comentFalhaItemPes = comentFalhaItemPes;
    }

    public String getDthrItemPes() {
        return dthrItemPes;
    }

    public void setDthrItemPes(String dthrItemPes) {
        this.dthrItemPes = dthrItemPes;
    }

    public Double getLatitudeItemPes() {
        return latitudeItemPes;
    }

    public void setLatitudeItemPes(Double latitudeItemPes) {
        this.latitudeItemPes = latitudeItemPes;
    }

    public Double getLongitudeItemPes() {
        return longitudeItemPes;
    }

    public void setLongitudeItemPes(Double longitudeItemPes) {
        this.longitudeItemPes = longitudeItemPes;
    }

    public Long getNroOSItemPes() {
        return nroOSItemPes;
    }

    public void setNroOSItemPes(Long nroOSItemPes) {
        this.nroOSItemPes = nroOSItemPes;
    }
}
