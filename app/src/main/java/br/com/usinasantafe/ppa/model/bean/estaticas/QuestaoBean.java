package br.com.usinasantafe.ppa.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbquestaoobsest")
public class QuestaoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idQuestao;
    @DatabaseField
    private Long idTopico;
    @DatabaseField
    private String descrQuestao;

    public QuestaoBean() {
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Long getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Long idTopico) {
        this.idTopico = idTopico;
    }

    public String getDescrQuestao() {
        return descrQuestao;
    }

    public void setDescrQuestao(String descrQuestao) {
        this.descrQuestao = descrQuestao;
    }
}
