package br.com.usinasantafe.ppa.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppa.model.bean.db.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
    @DatabaseField
    private Long matricFuncConfig;
    @DatabaseField
    private String senhaConfig;
    @DatabaseField
    private Long veiculoConfig;
    @DatabaseField
    private Long notaFiscalConfig;
    @DatabaseField
    private Long itemNFConfig;
    @DatabaseField
    private Long osConfig;

    public ConfigBean() {
    }

    public Long getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Long idConfig) {
        this.idConfig = idConfig;
    }

    public Long getMatricFuncConfig() {
        return matricFuncConfig;
    }

    public void setMatricFuncConfig(Long matricFuncConfig) {
        this.matricFuncConfig = matricFuncConfig;
    }

    public String getSenhaConfig() {
        return senhaConfig;
    }

    public void setSenhaConfig(String senhaConfig) {
        this.senhaConfig = senhaConfig;
    }

    public Long getVeiculoConfig() {
        return veiculoConfig;
    }

    public void setVeiculoConfig(Long veiculoConfig) {
        this.veiculoConfig = veiculoConfig;
    }

    public Long getNotaFiscalConfig() {
        return notaFiscalConfig;
    }

    public void setNotaFiscalConfig(Long notaFiscalConfig) {
        this.notaFiscalConfig = notaFiscalConfig;
    }

    public Long getItemNFConfig() {
        return itemNFConfig;
    }

    public void setItemNFConfig(Long itemNFConfig) {
        this.itemNFConfig = itemNFConfig;
    }

    public Long getOsConfig() {
        return osConfig;
    }

    public void setOsConfig(Long osConfig) {
        this.osConfig = osConfig;
    }
}
