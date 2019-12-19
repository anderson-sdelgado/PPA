package br.com.usinasantafe.ppa.model.dao;

import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;

public class PesagemDAO {

    public PesagemDAO() {
    }

    public void insPesagem(ConfigBean configBean, Double pesagem){
        ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
        itemPesagemBean.setIdVeiculos(configBean.getVeiculoConfig());
        itemPesagemBean.setIdNF(configBean.getNotaFiscalConfig());
        itemPesagemBean.setIdItemNF(configBean.getItemNFConfig());
    }

//    private Long idVeiculos;
//    private Long idNF;
//    private Long idItemNF;
//    private Long idOS;
//    private Double valorPesagem;
//    private String comentFalha;
//    private String dthrPesagem;
//    private Double latitudePesagem;
//    private Double longitudePesagem;
//    private Long statusConPesagem;
//    private Long statusPesagem;

}
