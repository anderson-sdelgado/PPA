package br.com.usinasantafe.ppa;

import android.app.Application;

import br.com.usinasantafe.ppa.control.ConfigCTR;
import br.com.usinasantafe.ppa.control.PesagemCTR;

public class PPAContext extends Application {

    public static String versaoAplic = "1.00";
    private PesagemCTR pesagemCTR;
    private ConfigCTR configCTR;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public PesagemCTR getPesagemCTR() {
        if (pesagemCTR == null)
            pesagemCTR = new PesagemCTR();
        return pesagemCTR;
    }

    public ConfigCTR getConfigCTR() {
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

}
