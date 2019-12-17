package br.com.usinasantafe.ppa.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualizarAplicativo extends AsyncTask<String ,Integer ,Boolean> {

    private ProgressDialog progressDialog;
    private Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);

        progressDialog.setMessage("ABAIXANDO ATUALIZAÇÃO...");

        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);

        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(progress[0]);
        String msg = "";
        if(progress[0]>99){

            msg="FINALIZADO DOWNLOAD... ";

        }else {

            msg="ABAIXANDO ATUALIZAÇÃO... "+progress[0]+"%";
        }
        progressDialog.setMessage(msg);

    }
    @Override
    protected void onPostExecute(Boolean result) {

        super.onPostExecute(result);

        progressDialog.dismiss();

        if(result){

            Toast.makeText(context,"DOWNLOAD TERMINADO",
                    Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(context,"FALHA NA ATUALIZAÇÃO",
                    Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    protected Boolean doInBackground(String... arg0) {
        Boolean flag = false;

        try {

            URL url = new URL("http://www.usinasantafe.com.br/pst/pst.apk");

            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH = Environment.getExternalStorageDirectory()+"/Download/";
            //String PATH = context.getFilesDir().getPath();
            File file = new File(PATH);
            file.mkdirs();

            File outputFile = new File(file,"pst.apk");

            if(outputFile.exists()){
                outputFile.delete();
            }

            InputStream is = c.getInputStream();

            int total_size = 2375196;//size of apk

            byte[] buffer = new byte[1024];
            int len1 = 0;
            int per = 0;
            int downloaded=0;
            FileOutputStream fos = new FileOutputStream(outputFile);

            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
                downloaded +=len1;
                per = (int) (downloaded * 100 / total_size);
                publishProgress(per);
            }
            fos.close();
            is.close();

            OpenNewVersion(PATH);

            flag = true;
        } catch (Exception e) {
            Log.e("PMM", "Update Error: " + e.getMessage());
            flag = false;
        }
        return flag;


    }

    void OpenNewVersion(String location) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(location + "/pst.apk")),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this.context, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
