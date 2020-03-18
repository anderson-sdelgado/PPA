package br.com.usinasantafe.ppa.util.conHttp;

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

import br.com.usinasantafe.ppa.control.PesagemCTR;
import br.com.usinasantafe.ppa.util.EnvioDadosServ;

public class PostMultipartGenerico extends AsyncTask<String, Void, String>   {

    private static PostMultipartGenerico instance = null;

    public PostMultipartGenerico() {
    }

    public static PostMultipartGenerico getInstance() {
        if (instance == null)
            instance = new PostMultipartGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... params) {

        String answer = "";

        String url = params[0];
        String cabec = params[1];
        String item = params[2];

		try{

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("cabec", cabec));
			valores.add(new BasicNameValuePair("item", item));

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
			if(result.trim().startsWith("GRAVOU")){
				PesagemCTR pesagemCTR = new PesagemCTR();
				pesagemCTR.deleteCabec(result);
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
