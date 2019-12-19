package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.bean.db.Entidade;

@DatabaseTable(tableName="tbcabpesvar")
public class CabPesagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabPes;
    @DatabaseField
    private Long idVeicCabPes;
    @DatabaseField
    private String fotoCabPes;
    @DatabaseField
    private String dthrCabPes;

    public CabPesagemBean() {
    }

    public Long getIdCabPes() {
        return idCabPes;
    }

    public void setIdCabPes(Long idCabPes) {
        this.idCabPes = idCabPes;
    }

    public Long getIdVeicCabPes() {
        return idVeicCabPes;
    }

    public void setIdVeicCabPes(Long idVeicCabPes) {
        this.idVeicCabPes = idVeicCabPes;
    }

    public String getFotoCabPes() {
        return fotoCabPes;
    }

    public void setFotoCabPes(String fotoCabPes) {
        this.fotoCabPes = fotoCabPes;
    }

    public String getDthrCabPes() {
        return dthrCabPes;
    }

    public void setDthrCabPes(String dthrCabPes) {
        this.dthrCabPes = dthrCabPes;
    }
}
