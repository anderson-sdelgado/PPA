package br.com.usinasantafe.ppa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.ppa.model.pst.DatabaseHelper;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;
import br.com.usinasantafe.ppa.util.Tempo;


public class AlarmClass extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PST", "DATA HORA = " + Tempo.getInstance().dataComHora());
		Log.i("PST", "STATUS = " + EnvioDadosServ.getInstance(). verifDadosEnvio());
		if(EnvioDadosServ.getInstance(). verifDadosEnvio()){
			Log.i("PST", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}
	}
}