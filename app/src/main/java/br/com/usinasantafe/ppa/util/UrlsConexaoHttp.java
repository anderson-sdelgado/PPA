package br.com.usinasantafe.ppa.util;

import br.com.usinasantafe.pst.PSTContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pst/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pst/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pst.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pst.util.UrlsConexaoHttp";

    public static String put = "?versao=" + PSTContext.versaoAplic.replace(".", "_");

    public static String AreaBean = urlPrincipal + "area.php" + put;
    public static String SubAreaBean = urlPrincipal + "subarea.php" + put;
    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String QuestaoBean = urlPrincipal + "questao.php" + put;
    public static String TipoBean = urlPrincipal + "tipo.php" + put;
    public static String TopicoBean = urlPrincipal + "topico.php" + put;
    public static String TurnoBean = urlPrincipal + "turno.php" + put;

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
