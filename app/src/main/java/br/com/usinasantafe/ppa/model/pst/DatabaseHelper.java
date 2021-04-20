package br.com.usinasantafe.ppa.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.ppa.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.OrdCarregBean;
import br.com.usinasantafe.ppa.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.CabecPesagemBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ppa.model.bean.variaveis.ItemPesagemBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "ppa_db";
	public static final int FORCA_BD_VERSION = 4;

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

				TableUtils.createTable(cs, OrdCarregBean.class);
				TableUtils.createTable(cs, FuncBean.class);
				TableUtils.createTable(cs, EquipBean.class);

				TableUtils.createTable(cs, ConfigBean.class);
				TableUtils.createTable(cs, CabecPesagemBean.class);
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

			if((oldVersion < 4) && (newVersion == 4)) {

				TableUtils.dropTable(cs, EquipBean.class, true);
				TableUtils.dropTable(cs, OrdCarregBean.class, true);
				TableUtils.dropTable(cs, ConfigBean.class, true);
				TableUtils.dropTable(cs, CabecPesagemBean.class, true);
				TableUtils.dropTable(cs, ItemPesagemBean.class, true);
				TableUtils.dropTable(cs, FuncBean.class, true);

				TableUtils.createTable(cs, OrdCarregBean.class);
				TableUtils.createTable(cs, FuncBean.class);
				TableUtils.createTable(cs, EquipBean.class);
				TableUtils.createTable(cs, ConfigBean.class);
				TableUtils.createTable(cs, CabecPesagemBean.class);
				TableUtils.createTable(cs, ItemPesagemBean.class);

			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
