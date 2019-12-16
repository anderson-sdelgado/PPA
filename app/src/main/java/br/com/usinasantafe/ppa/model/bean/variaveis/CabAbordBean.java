package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbcababordvar")
public class CabAbordBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabAbord;
    @DatabaseField
    private Long matricFuncCabAbord;
    @DatabaseField
    private Long idAreaCabAbord;
    @DatabaseField
    private Long idSubAreaCabAbord;
    @DatabaseField
    private Long idTurnoCabAbord;
    @DatabaseField
    private Long extensaoMinCabAbord;
    @DatabaseField
    private Long pessoasContCabAbord;
    @DatabaseField
    private Long pessoasObsCabAbord;
    @DatabaseField
    private Long tipoCabAbord;
    @DatabaseField
    private String comentCabAbord;
    @DatabaseField
    private String dthrCabAbord;
    @DatabaseField
    private Long statusCabAbord;

    public CabAbordBean() {
    }

    public Long getIdCabAbord() {
        return idCabAbord;
    }

    public void setIdCabAbord(Long idCabAbord) {
        this.idCabAbord = idCabAbord;
    }

    public Long getMatricFuncCabAbord() {
        return matricFuncCabAbord;
    }

    public void setMatricFuncCabAbord(Long matricFuncCabAbord) {
        this.matricFuncCabAbord = matricFuncCabAbord;
    }

    public Long getIdAreaCabAbord() {
        return idAreaCabAbord;
    }

    public void setIdAreaCabAbord(Long idAreaCabAbord) {
        this.idAreaCabAbord = idAreaCabAbord;
    }

    public Long getIdSubAreaCabAbord() {
        return idSubAreaCabAbord;
    }

    public void setIdSubAreaCabAbord(Long idSubAreaCabAbord) {
        this.idSubAreaCabAbord = idSubAreaCabAbord;
    }

    public Long getIdTurnoCabAbord() {
        return idTurnoCabAbord;
    }

    public void setIdTurnoCabAbord(Long idTurnoCabAbord) {
        this.idTurnoCabAbord = idTurnoCabAbord;
    }

    public Long getExtensaoMinCabAbord() {
        return extensaoMinCabAbord;
    }

    public void setExtensaoMinCabAbord(Long extensaoMinCabAbord) {
        this.extensaoMinCabAbord = extensaoMinCabAbord;
    }

    public Long getPessoasContCabAbord() {
        return pessoasContCabAbord;
    }

    public void setPessoasContCabAbord(Long pessoasContCabAbord) {
        this.pessoasContCabAbord = pessoasContCabAbord;
    }

    public Long getPessoasObsCabAbord() {
        return pessoasObsCabAbord;
    }

    public void setPessoasObsCabAbord(Long pessoasObsCabAbord) {
        this.pessoasObsCabAbord = pessoasObsCabAbord;
    }

    public Long getTipoCabAbord() {
        return tipoCabAbord;
    }

    public void setTipoCabAbord(Long tipoCabAbord) {
        this.tipoCabAbord = tipoCabAbord;
    }

    public String getComentCabAbord() {
        return comentCabAbord;
    }

    public void setComentCabAbord(String comentCabAbord) {
        this.comentCabAbord = comentCabAbord;
    }

    public String getDthrCabAbord() {
        return dthrCabAbord;
    }

    public void setDthrCabAbord(String dthrCabAbord) {
        this.dthrCabAbord = dthrCabAbord;
    }

    public Long getStatusCabAbord() {
        return statusCabAbord;
    }

    public void setStatusCabAbord(Long statusCabAbord) {
        this.statusCabAbord = statusCabAbord;
    }
}
