package br.com.usinasantafe.ppa.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class Imagem {

    public Imagem() {
    }

    public String getBitmapString(Bitmap foto){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        foto.compress(Bitmap.CompressFormat.JPEG, 95, stream);

        byte[] byteArray = stream.toByteArray();

        return (Base64.encodeToString(byteArray, Base64.DEFAULT));

    }

    public Bitmap getStringBitmap(String foto){

        try{
            byte [] encodeByte= Base64.decode(foto ,Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        }catch(Exception e){
            Log.i("PST", "Erro = " + e.toString());
            return null;
        }

    }

}
