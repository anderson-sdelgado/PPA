package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.bean.db.Entidade;

@DatabaseTable(tableName="tbpesagemvar")
public class PesagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idPesagem;
    @DatabaseField
    private Long idVeiculos;
    @DatabaseField
    private Long idNF;
    @DatabaseField
    private Long idItemNF;
    @DatabaseField
    private Long idOS;
    @DatabaseField
    private Double valorPesagem;
    @DatabaseField
    private String comentFalha;
    @DatabaseField
    private String dthrPesagem;
    @DatabaseField
    private Double latitudePesagem;
    @DatabaseField
    private Double longitudePesagem;
    @DatabaseField
    private Long statusConPesagem;  //0 - OffLine; 1 - OnLine
    @DatabaseField
    private Long statusPesagem;  //0 - Aberto; 1 - Enviar; 2 - Enviado

    public PesagemBean() {
    }

    public Long getIdPesagem() {
        return idPesagem;
    }

    public void setIdPesagem(Long idPesagem) {
        this.idPesagem = idPesagem;
    }

    public Long getIdVeiculos() {
        return idVeiculos;
    }

    public void setIdVeiculos(Long idVeiculos) {
        this.idVeiculos = idVeiculos;
    }

    public Long getIdNF() {
        return idNF;
    }

    public void setIdNF(Long idNF) {
        this.idNF = idNF;
    }

    public Long getIdItemNF() {
        return idItemNF;
    }

    public void setIdItemNF(Long idItemNF) {
        this.idItemNF = idItemNF;
    }

    public Long getIdOS() {
        return idOS;
    }

    public void setIdOS(Long idOS) {
        this.idOS = idOS;
    }

    public Double getValorPesagem() {
        return valorPesagem;
    }

    public void setValorPesagem(Double valorPesagem) {
        this.valorPesagem = valorPesagem;
    }

    public String getComentFalha() {
        return comentFalha;
    }

    public void setComentFalha(String comentFalha) {
        this.comentFalha = comentFalha;
    }

    public String getDthrPesagem() {
        return dthrPesagem;
    }

    public void setDthrPesagem(String dthrPesagem) {
        this.dthrPesagem = dthrPesagem;
    }

    public Double getLatitudePesagem() {
        return latitudePesagem;
    }

    public void setLatitudePesagem(Double latitudePesagem) {
        this.latitudePesagem = latitudePesagem;
    }

    public Double getLongitudePesagem() {
        return longitudePesagem;
    }

    public void setLongitudePesagem(Double longitudePesagem) {
        this.longitudePesagem = longitudePesagem;
    }

    public Long getStatusConPesagem() {
        return statusConPesagem;
    }

    public void setStatusConPesagem(Long statusConPesagem) {
        this.statusConPesagem = statusConPesagem;
    }

    public Long getStatusPesagem() {
        return statusPesagem;
    }

    public void setStatusPesagem(Long statusPesagem) {
        this.statusPesagem = statusPesagem;
    }
}
