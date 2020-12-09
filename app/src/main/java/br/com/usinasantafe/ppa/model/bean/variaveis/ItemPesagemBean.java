package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.pst.Entidade;

@DatabaseTable(tableName="tbitempesagemvar")
public class ItemPesagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemPesagem;
    @DatabaseField
    private Long idCabItemPesagem;
    @DatabaseField
    private Long matricFuncItemPesagem;
    @DatabaseField
    private String prodItemPesagem;
    @DatabaseField
    private Long nroOSItemPesagem;
    @DatabaseField
    private Double pesoItemPesagem;
    @DatabaseField
    private String comentFalhaItemPesagem;
    @DatabaseField
    private String dthrItemPesagem;
    @DatabaseField
    private Double latitudeItemPesagem;
    @DatabaseField
    private Double longitudeItemPesagem;

    public ItemPesagemBean() {
    }

    public Long getIdItemPesagem() {
        return idItemPesagem;
    }

    public void setIdItemPesagem(Long idItemPesagem) {
        this.idItemPesagem = idItemPesagem;
    }

    public Long getIdCabItemPesagem() {
        return idCabItemPesagem;
    }

    public void setIdCabItemPesagem(Long idCabItemPesagem) {
        this.idCabItemPesagem = idCabItemPesagem;
    }

    public Long getMatricFuncItemPesagem() {
        return matricFuncItemPesagem;
    }

    public void setMatricFuncItemPesagem(Long matricFuncItemPesagem) {
        this.matricFuncItemPesagem = matricFuncItemPesagem;
    }

    public String getProdItemPesagem() {
        return prodItemPesagem;
    }

    public void setProdItemPesagem(String prodItemPesagem) {
        this.prodItemPesagem = prodItemPesagem;
    }

    public Double getPesoItemPesagem() {
        return pesoItemPesagem;
    }

    public void setPesoItemPesagem(Double pesoItemPesagem) {
        this.pesoItemPesagem = pesoItemPesagem;
    }

    public String getComentFalhaItemPesagem() {
        return comentFalhaItemPesagem;
    }

    public void setComentFalhaItemPesagem(String comentFalhaItemPesagem) {
        this.comentFalhaItemPesagem = comentFalhaItemPesagem;
    }

    public String getDthrItemPesagem() {
        return dthrItemPesagem;
    }

    public void setDthrItemPesagem(String dthrItemPesagem) {
        this.dthrItemPesagem = dthrItemPesagem;
    }

    public Double getLatitudeItemPesagem() {
        return latitudeItemPesagem;
    }

    public void setLatitudeItemPesagem(Double latitudeItemPesagem) {
        this.latitudeItemPesagem = latitudeItemPesagem;
    }

    public Double getLongitudeItemPesagem() {
        return longitudeItemPesagem;
    }

    public void setLongitudeItemPesagem(Double longitudeItemPesagem) {
        this.longitudeItemPesagem = longitudeItemPesagem;
    }

    public Long getNroOSItemPesagem() {
        return nroOSItemPesagem;
    }

    public void setNroOSItemPesagem(Long nroOSItemPesagem) {
        this.nroOSItemPesagem = nroOSItemPesagem;
    }
}
