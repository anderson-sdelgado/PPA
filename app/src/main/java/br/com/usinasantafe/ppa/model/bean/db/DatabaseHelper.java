package br.com.usinasantafe.ppa.model.bean.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.ppa.model.bean.estaticas.VeiculoBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.ItemNFBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.NotaFiscalBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.CabPesagemBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "ppa_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, FORCA_DB_NAME,
				null, FORCA_BD_VERSION);

		instance = this;
		
	}

	@Override
	public void close() {

		super.close();
		
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {

		
		try{
			
			TableUtils.createTable(cs, VeiculoBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, ItemNFBean.class);
			TableUtils.createTable(cs, NotaFiscalBean.class);
			TableUtils.createTable(cs, OSBean.class);

			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, CabPesagemBean.class);
			TableUtils.createTable(cs, ItemPesagemBean.class);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
