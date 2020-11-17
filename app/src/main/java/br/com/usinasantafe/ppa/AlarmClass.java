package br.com.usinasantafe.ppa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

import br.com.usinasantafe.ppa.model.bean.variaveis.CabPesagemBean;
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

		CabPesagemBean cabPesagemBean = new CabPesagemBean();
		List<CabPesagemBean> cabPesagemList = cabPesagemBean.all();

		for(CabPesagemBean cabPesagemBD : cabPesagemList){
			Log.i("PPA", "idCabPes = " + cabPesagemBD.getIdCabPes());
			Log.i("PPA", "idEquipCabPes = " + cabPesagemBD.getIdEquipCabPes());
			Log.i("PPA", "matricFuncCabPes = " + cabPesagemBD.getMatricFuncCabPes());
			Log.i("PPA", "placaVeicCabPes = " + cabPesagemBD.getPlacaVeicCabPes());
			Log.i("PPA", "dthrCabPes = " + cabPesagemBD.getDthrCabPes());
			Log.i("PPA", "statusConCabPes = " + cabPesagemBD.getStatusConCabPes());
			Log.i("PPA", "statusApontCabPes = " + cabPesagemBD.getStatusApontCabPes());
			Log.i("PPA", "statusCabPes = " + cabPesagemBD.getStatusCabPes());
		}

		cabPesagemList.clear();

		ItemPesagemBean itemPesagemBean = new ItemPesagemBean();
		List<ItemPesagemBean> itemPesagemList = itemPesagemBean.all();

		for(ItemPesagemBean itemPesagemBD : itemPesagemList){
			Log.i("PPA", "idItemPes = " + itemPesagemBD.getIdItemPes());
			Log.i("PPA", "idCabItemPes = " + itemPesagemBD.getIdCabItemPes());
			Log.i("PPA", "prodItemPes = " + itemPesagemBD.getProdItemPes());
			Log.i("PPA", "nroOSItemPes = " + itemPesagemBD.getNroOSItemPes());
			Log.i("PPA", "pesoItemPes = " + itemPesagemBD.getPesoItemPes());
			Log.i("PPA", "comentFalhaItemPes = " + itemPesagemBD.getComentFalhaItemPes());
			Log.i("PST", "dthrItemPes = " + itemPesagemBD.getDthrItemPes());
			Log.i("PST", "latitudeItemPes = " + itemPesagemBD.getLatitudeItemPes());
			Log.i("PST", "longitudeItemPes = " + itemPesagemBD.getLongitudeItemPes());
		}

		itemPesagemList.clear();

	}
}