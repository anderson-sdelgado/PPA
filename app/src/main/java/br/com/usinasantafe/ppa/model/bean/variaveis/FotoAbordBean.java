package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbfotoabordvar")
public class FotoAbordBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idFotoAbord;
    @DatabaseField
    private Long idCabFotoAbord;
    @DatabaseField
    private String fotoAbord;
    @DatabaseField
    private String dthrFotoAbord;

    public FotoAbordBean() {
    }

    public Long getIdFotoAbord() {
        return idFotoAbord;
    }

    public void setIdFotoAbord(Long idFotoAbord) {
        this.idFotoAbord = idFotoAbord;
    }

    public Long getIdCabFotoAbord() {
        return idCabFotoAbord;
    }

    public void setIdCabFotoAbord(Long idCabFotoAbord) {
        this.idCabFotoAbord = idCabFotoAbord;
    }

    public String getDthrFotoAbord() {
        return dthrFotoAbord;
    }

    public void setDthrFotoAbord(String dthrFotoAbord) {
        this.dthrFotoAbord = dthrFotoAbord;
    }

    public String getFotoAbord() {
        return fotoAbord;
    }

    public void setFotoAbord(String fotoAbord) {
        this.fotoAbord = fotoAbord;
    }
}
