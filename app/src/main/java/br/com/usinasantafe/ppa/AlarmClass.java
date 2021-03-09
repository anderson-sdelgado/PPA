package br.com.usinasantafe.ppa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.CabecPesagemBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;
import br.com.usinasantafe.ppa.model.pst.DatabaseHelper;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;
import br.com.usinasantafe.ppa.util.Tempo;


public class AlarmClass extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		exibir();

		Log.i("PST", "DATA HORA = " + Tempo.getInstance().dataComHora());
		Log.i("PST", "STATUS = " + EnvioDadosServ.getInstance(). verifDadosEnvio());
		if(EnvioDadosServ.getInstance(). verifDadosEnvio()){
			Log.i("PST", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}
	}

	public void exibir(){

		CabecPesagemBean cabecPesagemBean = new CabecPesagemBean();
		List<CabecPesagemBean> cabPesagemList = cabecPesagemBean.all();

		for(CabecPesagemBean cabPesagemBD : cabPesagemList){
			Log.i("PPA", "idCabPes = " + cabPesagemBD.getIdCabecPesagem());
			Log.i("PPA", "idEquipCabPes = " + cabPesagemBD.getIdEquipCabecPesagem());
			Log.i("PPA", "idOrdCarregCabPes = " + cabPesagemBD.getIdOrdCarregCabecPesagem());
			Log.i("PPA", "placaVeicCabPes = " + cabPesagemBD.getPlacaVeicCabecPesagem());
			Log.i("PPA", "dthrCabPes = " + cabPesagemBD.getDthrInicialCabecPesagem());
			Log.i("PPA", "statusConCabPes = " + cabPesagemBD.getStatusConCabecPesagem());
			Log.i("PPA", "statusApontCabPes = " + cabPesagemBD.getStatusApontCabecPesagem());
			Log.i("PPA", "statusCabPes = " + cabPesagemBD.getStatusCabecPesagem());
		}

		cabPesagemList.clear();

		ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
		List<ItemPesagemBean> itemPesagemList = itemPesagemBean.all();

		for(ItemPesagemBean itemPesagemBD : itemPesagemList){
			Log.i("PPA", "idItemPes = " + itemPesagemBD.getIdItemPesagem());
			Log.i("PPA", "idCabItemPes = " + itemPesagemBD.getIdCabItemPesagem());
			Log.i("PPA", "seqItemPes = " + itemPesagemBD.getSeqItemPesagem());
			Log.i("PPA", "matricFuncItemPes = " + itemPesagemBD.getMatricFuncItemPesagem());
			Log.i("PPA", "prodItemPes = " + itemPesagemBD.getProdItemPesagem());
			Log.i("PPA", "nroOSItemPes = " + itemPesagemBD.getNroOSItemPesagem());
			Log.i("PPA", "pesoItemPes = " + itemPesagemBD.getPesoItemPesagem());
			Log.i("PPA", "comentFalhaItemPes = " + itemPesagemBD.getComentFalhaItemPesagem());
			Log.i("PST", "dthrItemPes = " + itemPesagemBD.getDthrItemPesagem());
			Log.i("PST", "latitudeItemPes = " + itemPesagemBD.getLatitudeItemPesagem());
			Log.i("PST", "longitudeItemPes = " + itemPesagemBD.getLongitudeItemPesagem());
		}

		itemPesagemList.clear();

	}
}