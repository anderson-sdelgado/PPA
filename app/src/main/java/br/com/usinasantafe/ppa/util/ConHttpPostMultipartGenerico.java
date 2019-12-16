package br.com.usinasantafe.ppa.util;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

import br.com.usinasantafe.pst.control.AbordagemCTR;

public class ConHttpPostMultipartGenerico extends AsyncTask<String, Void, String>   {

    private static ConHttpPostMultipartGenerico instance = null;

    public ConHttpPostMultipartGenerico() {
    }

    public static ConHttpPostMultipartGenerico getInstance() {
        if (instance == null)
            instance = new ConHttpPostMultipartGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... params) {

        String answer = "";

        String url = params[0];
        String cabec = params[1];
        String item = params[2];
		String foto1 = params[3];
		String foto2 = params[4];
		String foto3 = params[5];
		String foto4 = params[6];

		try{

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("cabec", cabec));
			valores.add(new BasicNameValuePair("item", item));
            valores.add(new BasicNameValuePair("foto1", foto1));
			valores.add(new BasicNameValuePair("foto2", foto2));
			valores.add(new BasicNameValuePair("foto3", foto3));
			valores.add(new BasicNameValuePair("foto4", foto4));

            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            HttpResponse resposta = httpClient.execute(httpPost);
            answer = EntityUtils.toString(resposta.getEntity());

		} catch (Exception e) {
			EnvioDadosServ.getInstance().setEnviando(false);
		}
		
		return answer;
	}

	protected void onPostExecute(String result) {

		try {

			Log.i("ECM", "VALOR RECEBIDO --> " + result);
			if(result.trim().contains("GRAVOU")){
				AbordagemCTR abordagemCTR = new AbordagemCTR();
				abordagemCTR.deleteCabec(result);
			}
			else{
				EnvioDadosServ.getInstance().setEnviando(false);
			}
		} catch (Exception e) {
			EnvioDadosServ.getInstance().setEnviando(false);
			Log.i("PMM", "Erro = " + e);
		}

	}
	
}
