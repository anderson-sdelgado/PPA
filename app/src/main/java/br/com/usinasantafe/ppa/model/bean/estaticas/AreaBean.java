package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbareaest")
public class AreaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idArea;
    @DatabaseField
    private String descrArea;

    public AreaBean() {
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getDescrArea() {
        return descrArea;
    }

    public void setDescrArea(String descrArea) {
        this.descrArea = descrArea;
    }
}
