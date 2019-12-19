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
    private Long idNFItemPes;
    @DatabaseField
    private Long idItNFItemPes;
    @DatabaseField
    private Long idOSItemPes;
    @DatabaseField
    private Double valorItemPes;
    @DatabaseField
    private String comentFalhaItemPes;
    @DatabaseField
    private String dthrItemPes;
    @DatabaseField
    private Double latitudeItemPes;
    @DatabaseField
    private Double longitudeItemPes;
    @DatabaseField
    private Long statusConItemPes;  //0 - OffLine; 1 - OnLine
    @DatabaseField
    private Long statusItemPes;  //0 - Aberto; 1 - Enviar; 2 - Enviado

    public ItemPesagemBean() {
    }

    public Long getIdItemPes() {
        return idItemPes;
    }

    public void setIdItemPes(Long idItemPes) {
        this.idItemPes = idItemPes;
    }

    public Long getIdNFItemPes() {
        return idNFItemPes;
    }

    public void setIdNFItemPes(Long idNFItemPes) {
        this.idNFItemPes = idNFItemPes;
    }

    public Long getIdItNFItemPes() {
        return idItNFItemPes;
    }

    public void setIdItNFItemPes(Long idItNFItemPes) {
        this.idItNFItemPes = idItNFItemPes;
    }

    public Long getIdOSItemPes() {
        return idOSItemPes;
    }

    public void setIdOSItemPes(Long idOSItemPes) {
        this.idOSItemPes = idOSItemPes;
    }

    public Double getValorItemPes() {
        return valorItemPes;
    }

    public void setValorItemPes(Double valorItemPes) {
        this.valorItemPes = valorItemPes;
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

    public Long getStatusConItemPes() {
        return statusConItemPes;
    }

    public void setStatusConItemPes(Long statusConItemPes) {
        this.statusConItemPes = statusConItemPes;
    }

    public Long getStatusItemPes() {
        return statusItemPes;
    }

    public void setStatusItemPes(Long statusItemPes) {
        this.statusItemPes = statusItemPes;
    }
}
