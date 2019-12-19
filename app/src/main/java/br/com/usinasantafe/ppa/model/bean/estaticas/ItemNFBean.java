package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.bean.db.Entidade;

@DatabaseTable(tableName="tbitemnfest")
public class ItemNFBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idItemNF;
    @DatabaseField
    private Long idNF;
    @DatabaseField
    private String codProd;

    public ItemNFBean() {
    }

    public Long getIdItemNF() {
        return idItemNF;
    }

    public void setIdItemNF(Long idItemNF) {
        this.idItemNF = idItemNF;
    }

    public Long getIdNF() {
        return idNF;
    }

    public void setIdNF(Long idNF) {
        this.idNF = idNF;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }
}
