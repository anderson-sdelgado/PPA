package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.bean.db.Entidade;

@DatabaseTable(tableName="tbitempesvar")
public class ItemPesagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemPes;
    @DatabaseField
    private Long idCabItemPes;
    @DatabaseField
    private Long nroNFItemPes;
    @DatabaseField
    private String codItNFItemPes;
    @DatabaseField
    private String nroOSItemPes;
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
    @DatabaseField
    private Long statusConOSItemPes;  //0 - OffLine; 1 - OnLine

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

    public Long getNroNFItemPes() {
        return nroNFItemPes;
    }

    public void setNroNFItemPes(Long nroNFItemPes) {
        this.nroNFItemPes = nroNFItemPes;
    }

    public String getCodItNFItemPes() {
        return codItNFItemPes;
    }

    public void setCodItNFItemPes(String codItNFItemPes) {
        this.codItNFItemPes = codItNFItemPes;
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

    public Long getStatusConOSItemPes() {
        return statusConOSItemPes;
    }

    public void setStatusConOSItemPes(Long statusConOSItemPes) {
        this.statusConOSItemPes = statusConOSItemPes;
    }

    public String getNroOSItemPes() {
        return nroOSItemPes;
    }

    public void setNroOSItemPes(String nroOSItemPes) {
        this.nroOSItemPes = nroOSItemPes;
    }
}
