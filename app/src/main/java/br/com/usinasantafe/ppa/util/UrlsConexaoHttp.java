package br.com.usinasantafe.ppa.util;

import br.com.usinasantafe.ppa.PPAContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/ppadev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/ppadev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.ppa.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.ppa.util.UrlsConexaoHttp";

    public static String put = "?versao=" + PPAContext.versaoAplic.replace(".", "_");

    public static String EquipBean = urlPrincipal + "equip.php" + put;
    public static String FuncBean = urlPrincipal + "func.php" + put;
    public static String ItemNotaFiscalBean = urlPrincipal + "itemnotafiscal.php" + put;
    public static String NotaFiscalBean = urlPrincipal + "notafiscal.php" + put;
    public static String OSBean = urlPrincipal + "os.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirDados() {
        return urlPrincEnvio + "inserirdados.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        }
        return retorno;
    }

}
