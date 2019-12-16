package br.com.usinasantafe.ppa.model.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

import br.com.usinasantafe.pst.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.util.Tempo;

public class FotoAbordDAO {

    public FotoAbordDAO() {
    }

    public FotoAbordBean salvarFoto(Long idCabAbord, Bitmap bitmap){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        fotoAbordBean.setIdCabFotoAbord(idCabAbord);
        fotoAbordBean.setFotoAbord(getBitmapString(bitmap));
        fotoAbordBean.setDthrFotoAbord(Tempo.getInstance().dataComHora());
        fotoAbordBean.insert();
        return fotoAbordBean;
    }

    public List getListFotoCabecAbert(Long idCabec){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        return fotoAbordBean.get("idCabFotoAbord", idCabec);
    }

    private String getBitmapString(Bitmap foto){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        foto.compress(Bitmap.CompressFormat.JPEG, 95, stream);

        byte[] byteArray = stream.toByteArray();

        return(Base64.encodeToString(byteArray, Base64.DEFAULT));

    }

    public Bitmap getStringBitmap(String foto){

        try{
            byte [] encodeByte= Base64.decode(foto ,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            Log.i("PST", "Erro = " + e.toString());
            return null;
        }

    }

    public void delFotoCabec(Long idCabec){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        List fotoAbordList = fotoAbordBean.get("idCabFotoAbord", idCabec);
        for (int i = 0; i < fotoAbordList.size(); i++) {
            fotoAbordBean = (FotoAbordBean) fotoAbordList.get(i);
            fotoAbordBean.delete();
        }
    }

}
