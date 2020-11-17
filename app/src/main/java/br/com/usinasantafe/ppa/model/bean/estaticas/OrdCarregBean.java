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
    private String prodOrdCarreg;

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

    public String getProdOrdCarreg() {
        return prodOrdCarreg;
    }

    public void setProdOrdCarreg(String prodOrdCarreg) {
        this.prodOrdCarreg = prodOrdCarreg;
    }
}
