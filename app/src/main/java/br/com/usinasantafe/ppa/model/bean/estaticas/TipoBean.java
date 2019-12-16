package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbtipoobsest")
public class TipoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTipo;
    @DatabaseField
    private String descrTipo;

    public TipoBean() {
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescrTipo() {
        return descrTipo;
    }

    public void setDescrTipo(String descrTipo) {
        this.descrTipo = descrTipo;
    }
}
