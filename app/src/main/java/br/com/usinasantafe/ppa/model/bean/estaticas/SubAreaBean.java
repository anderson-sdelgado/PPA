package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbsubareaest")
public class SubAreaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idSubArea;
    @DatabaseField
    private Long idArea;
    @DatabaseField
    private String descrSubArea;

    public SubAreaBean() {
    }

    public Long getIdSubArea() {
        return idSubArea;
    }

    public void setIdSubArea(Long idSubArea) {
        this.idSubArea = idSubArea;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getDescrSubArea() {
        return descrSubArea;
    }

    public void setDescrSubArea(String descrSubArea) {
        this.descrSubArea = descrSubArea;
    }
}
