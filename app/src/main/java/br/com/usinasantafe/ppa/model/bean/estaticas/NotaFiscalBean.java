package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.pst.Entidade;

@DatabaseTable(tableName="tbnfest")
public class NotaFiscalBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idNF;
    @DatabaseField
    private String nroNF;

    public NotaFiscalBean() {
    }

    public Long getIdNF() {
        return idNF;
    }

    public void setIdNF(Long idNF) {
        this.idNF = idNF;
    }

    public String getNroNF() {
        return nroNF;
    }

    public void setNroNF(String nroNF) {
        this.nroNF = nroNF;
    }
}
