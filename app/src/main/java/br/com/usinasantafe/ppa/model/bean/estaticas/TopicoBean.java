package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbtopicoobsest")
public class TopicoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTopico;
    @DatabaseField
    private Long idTipo;
    @DatabaseField
    private String descrTopico;

    public TopicoBean() {
    }

    public Long getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescrTopico() {
        return descrTopico;
    }

    public void setDescrTopico(String descrTopico) {
        this.descrTopico = descrTopico;
    }
}
