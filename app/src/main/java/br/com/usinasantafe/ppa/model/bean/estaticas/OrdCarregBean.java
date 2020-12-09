package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.pst.Entidade;

@DatabaseTable(tableName="tbordcarregest")
public class OrdCarregBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idOrdCarreg;
    @DatabaseField
    private Long idBDOrdCarreg;
    @DatabaseField
    private String placaVeicOrdCarreg;
    @DatabaseField
    private Long nroOSOrdCarreg;
    @DatabaseField
    private Long nroNFOrdCarreg;
    @DatabaseField
    private String codProdOrdCarreg;
    @DatabaseField
    private String descrProdOrdCarreg;
    @DatabaseField
    private String dataOrdCarreg;
    @DatabaseField
    private Double pesoProdOrdCarreg;

    public OrdCarregBean() {
    }

    public Long getIdOrdCarreg() {
        return idOrdCarreg;
    }

    public void setIdOrdCarreg(Long idOrdCarreg) {
        this.idOrdCarreg = idOrdCarreg;
    }

    public Long getIdBDOrdCarreg() {
        return idBDOrdCarreg;
    }

    public void setIdBDOrdCarreg(Long idBDOrdCarreg) {
        this.idBDOrdCarreg = idBDOrdCarreg;
    }

    public String getPlacaVeicOrdCarreg() {
        return placaVeicOrdCarreg;
    }

    public void setPlacaVeicOrdCarreg(String placaVeicOrdCarreg) {
        this.placaVeicOrdCarreg = placaVeicOrdCarreg;
    }

    public Long getNroOSOrdCarreg() {
        return nroOSOrdCarreg;
    }

    public void setNroOSOrdCarreg(Long nroOSOrdCarreg) {
        this.nroOSOrdCarreg = nroOSOrdCarreg;
    }

    public Long getNroNFOrdCarreg() {
        return nroNFOrdCarreg;
    }

    public void setNroNFOrdCarreg(Long nroNFOrdCarreg) {
        this.nroNFOrdCarreg = nroNFOrdCarreg;
    }

    public String getCodProdOrdCarreg() {
        return codProdOrdCarreg;
    }

    public void setCodProdOrdCarreg(String codProdOrdCarreg) {
        this.codProdOrdCarreg = codProdOrdCarreg;
    }

    public String getDescrProdOrdCarreg() {
        return descrProdOrdCarreg;
    }

    public void setDescrProdOrdCarreg(String descrProdOrdCarreg) {
        this.descrProdOrdCarreg = descrProdOrdCarreg;
    }

    public String getDataOrdCarreg() {
        return dataOrdCarreg;
    }

    public void setDataOrdCarreg(String dataOrdCarreg) {
        this.dataOrdCarreg = dataOrdCarreg;
    }

    public Double getPesoProdOrdCarreg() {
        return pesoProdOrdCarreg;
    }

    public void setPesoProdOrdCarreg(Double pesoProdOrdCarreg) {
        this.pesoProdOrdCarreg = pesoProdOrdCarreg;
    }
}
