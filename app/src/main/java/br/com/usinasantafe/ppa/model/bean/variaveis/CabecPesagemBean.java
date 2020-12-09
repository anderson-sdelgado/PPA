package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.pst.Entidade;

@DatabaseTable(tableName="tbcabecpesagemvar")
public class CabecPesagemBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecPesagem;
    @DatabaseField
    private Long idEquipCabecPesagem;
    @DatabaseField
    private Long idOrdCarregCabecPesagem;
    @DatabaseField
    private String placaVeicCabecPesagem;
    @DatabaseField
    private String dthrInicialCabecPesagem;
    @DatabaseField
    private String dthrFinalCabecPesagem;
    @DatabaseField
    private Long statusConCabecPesagem; //0 - OffLine; 1 - OnLine; 2 - Sem Informação
    @DatabaseField
    private Long statusApontCabecPesagem; //0 - Aberto; 1 - Apont;
    @DatabaseField
    private Long statusCabecPesagem; //0 - Criado; 1 - Aberto; 2 - Fechado;

    public CabecPesagemBean() {
    }

    public Long getIdCabecPesagem() {
        return idCabecPesagem;
    }

    public void setIdCabecPesagem(Long idCabecPesagem) {
        this.idCabecPesagem = idCabecPesagem;
    }

    public Long getIdEquipCabecPesagem() {
        return idEquipCabecPesagem;
    }

    public void setIdEquipCabecPesagem(Long idEquipCabecPesagem) {
        this.idEquipCabecPesagem = idEquipCabecPesagem;
    }

    public Long getIdOrdCarregCabecPesagem() {
        return idOrdCarregCabecPesagem;
    }

    public void setIdOrdCarregCabecPesagem(Long idOrdCarregCabecPesagem) {
        this.idOrdCarregCabecPesagem = idOrdCarregCabecPesagem;
    }

    public String getPlacaVeicCabecPesagem() {
        return placaVeicCabecPesagem;
    }

    public void setPlacaVeicCabecPesagem(String placaVeicCabecPesagem) {
        this.placaVeicCabecPesagem = placaVeicCabecPesagem;
    }

    public String getDthrInicialCabecPesagem() {
        return dthrInicialCabecPesagem;
    }

    public void setDthrInicialCabecPesagem(String dthrInicialCabecPesagem) {
        this.dthrInicialCabecPesagem = dthrInicialCabecPesagem;
    }

    public String getDthrFinalCabecPesagem() {
        return dthrFinalCabecPesagem;
    }

    public void setDthrFinalCabecPesagem(String dthrFinalCabecPesagem) {
        this.dthrFinalCabecPesagem = dthrFinalCabecPesagem;
    }

    public Long getStatusConCabecPesagem() {
        return statusConCabecPesagem;
    }

    public void setStatusConCabecPesagem(Long statusConCabecPesagem) {
        this.statusConCabecPesagem = statusConCabecPesagem;
    }

    public Long getStatusApontCabecPesagem() {
        return statusApontCabecPesagem;
    }

    public void setStatusApontCabecPesagem(Long statusApontCabecPesagem) {
        this.statusApontCabecPesagem = statusApontCabecPesagem;
    }

    public Long getStatusCabecPesagem() {
        return statusCabecPesagem;
    }

    public void setStatusCabecPesagem(Long statusCabecPesagem) {
        this.statusCabecPesagem = statusCabecPesagem;
    }
}
