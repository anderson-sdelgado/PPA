package br.com.usinasantafe.ppa.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

import br.com.usinasantafe.ppa.model.bean.db.GenericRecordable;


public class UpdateBD {

	private ArrayList tabAtualArrayList;
	private static UpdateBD instance = null;
	private int contAtualBD = 0;
	private String classe = "";
	private ProgressDialog progressDialog;
	private int qtdeBD = 0;
	private GenericRecordable genericRecordable;
	private Context context;
	private int tipoReceb;
	private Context telaAtual;
	private Class telaProx;
	private UrlsConexaoHttp urlsConexaoHttp;
	
	public UpdateBD() {

		genericRecordable = new GenericRecordable();
	}
	
    public static UpdateBD getInstance() {
        if (instance == null)
        instance = new UpdateBD();
        return instance;
    }

	@SuppressWarnings("unchecked")
	public void manipularDadosHttp(String tipo, String result){

		if(!result.equals("")){

			try{

				Log.i("PMM", "TIPO -> " + tipo);
				Log.i("PMM", "RESULT -> " + result);

				JSONObject jObj = new JSONObject(result);
				JSONArray jsonArray = jObj.getJSONArray("dados");
				Class classe = Class.forName(manipLocalClasse(tipo));
				genericRecordable.deleteAll(classe);

				for(int i = 0; i < jsonArray.length(); i++){

					JSONObject objeto = jsonArray.getJSONObject(i);
					Gson gson = new Gson();
					genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

				}

				Log.i("PMM", " SALVOU DADOS ");

				if(contAtualBD > 0){
					atualizandoBD();
				}

			}
			catch (Exception e) {
			Log.i("PMM", "Erro Manip = " + e);
			}

		}
		else{
			encerrar();
		}

	}


	public void atualizarBD(ProgressDialog progressDialog){

		try {

			this.tipoReceb = 1;
			this.progressDialog = progressDialog;
			tabAtualArrayList = new ArrayList();
	        Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl);

	        for (Field field : retClasse.getDeclaredFields()) {
	            String campo = field.getName();
	            Log.i("PMM", "Campo = " + campo);
	            if(campo.contains("Bean")){
	            	tabAtualArrayList.add(campo);
	            }

	        }

	        classe = (String) tabAtualArrayList.get(contAtualBD);

	        String[] url = {classe};

		    contAtualBD++;

	        ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
	        conHttpGetBDGenerico.execute(url);

		} catch (Exception e) {
			Log.i("PMM", "ERRO Manip2 = " + e);
		}

	}

	public void atualGenericoBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, ArrayList classeArrayList){

		try {

			this.tipoReceb = 1;
			this.telaAtual = telaAtual;
			this.telaProx = telaProx;
			this.progressDialog = progressDialog;
			tabAtualArrayList = new ArrayList();

			Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl);

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				Log.i("PMM", "Campo = " + campo);
				for (int i = 0; i < classeArrayList.size(); i++) {
					String classe = (String) classeArrayList.get(i);
					if(campo.equals(classe)){
						tabAtualArrayList.add(campo);
					}
				}
			}

			classe = (String) tabAtualArrayList.get(contAtualBD);
			String[] url = {classe};
			contAtualBD++;

			ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
			conHttpGetBDGenerico.execute(url);

		} catch (Exception e) {
			Log.i("PMM", "ERRO = " + e);
		}

	}

	public void atualizandoBD(){

		if(this.tipoReceb == 1){

			qtdeBD = tabAtualArrayList.size();

			if(contAtualBD < tabAtualArrayList.size()){

				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe};
				contAtualBD++;

				ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
		        conHttpGetBDGenerico.execute(url);

			}
			else{

				this.progressDialog.dismiss();
				contAtualBD = 0;

				AlertDialog.Builder alerta = new AlertDialog.Builder(this.context);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("FOI ATUALIZADO COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

				alerta.show();
			}

		}
		else if(this.tipoReceb == 2){

			qtdeBD = tabAtualArrayList.size();

			if(contAtualBD < tabAtualArrayList.size()){

		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe};
				contAtualBD++;

				ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
		        conHttpGetBDGenerico.execute(url);

			}
			else
			{
				contAtualBD = 0;
			}

		}

	}

	public void encerrar(){

		if(this.tipoReceb == 1){

			this.progressDialog.dismiss();
			AlertDialog.Builder alerta = new AlertDialog.Builder(this.context);
			alerta.setTitle("ATENCAO");
			alerta.setMessage("FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
			alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});

			alerta.show();

		}
	}

	public String manipLocalClasse(String classe){
	    if(classe.contains("Bean")){
	    	classe = urlsConexaoHttp.localPSTEstatica + classe;
	    }
		return classe;
	}

	public void setContext(Context context){
		this.context = context;
	}
	
}
