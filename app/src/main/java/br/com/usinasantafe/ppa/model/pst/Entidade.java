package br.com.usinasantafe.ppa.model.pst;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes","unchecked"})
public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;
	private Dao dao;
	
	private Dao daoImpl(){
		
		try {
			
			if(dao == null){
				DatabaseHelper instance = DatabaseHelper.getInstance();
				dao = instance.getDao(getClass());
			}
			
			return dao;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	public void insert() {
		try {
			this.daoImpl().create(this);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update() {
		
		try {
			this.daoImpl().update(this);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete()  {
		try {
			this.daoImpl().delete(this);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deleteAll()  {
		try {
			DeleteBuilder<String, Object> deleteBuilder = this.daoImpl().deleteBuilder();
			deleteBuilder.delete();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List all() {
		try {
			return this.daoImpl().queryForAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List get(String campo, Object valor) {
		try {
			return this.daoImpl().queryForEq(campo, valor);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List get(ArrayList<EspecificaPesquisa> pesquisaList) {
		try {
			QueryBuilder<String, Object> queryBuilder =
					this.daoImpl().queryBuilder();
			Where<String, Object> where = queryBuilder.where();
			for(int i = 0; i < pesquisaList.size(); i++) {
				EspecificaPesquisa pesquisa = pesquisaList.get(i);
				if(i > 0){
					where.and();
				}
				if(pesquisa.getTipo() == 1) {
					where.eq(pesquisa.getCampo(), pesquisa.getValor());
				}else {
					where.ne(pesquisa.getCampo(), pesquisa.getValor());
				}
			}
			PreparedQuery preparedQuery = queryBuilder.prepare();
			return this.daoImpl().query(preparedQuery);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List getAndOrderBy(String campo, Object valor, String campOrder, boolean order) {

		try {
			QueryBuilder<String, Object> queryBuilder =
					this.daoImpl().queryBuilder();
			Where<String, Object> where = queryBuilder.where();
			where.eq(campo, valor);
			queryBuilder.orderBy(campOrder, order);
			PreparedQuery preparedQuery = queryBuilder.prepare();
			return this.daoImpl().query(preparedQuery);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List getAndOrderBy(ArrayList<EspecificaPesquisa> pesquisaList, String campo, boolean order) {
		try {
			QueryBuilder<String, Object> queryBuilder =
					this.daoImpl().queryBuilder();
			Where<String, Object> where = queryBuilder.where();
			for(int i = 0; i < pesquisaList.size(); i++) {
				EspecificaPesquisa pesquisa = pesquisaList.get(i);
				if(i > 0){
					where.and();
				}
				if(pesquisa.getTipo() == 1) {
					where.eq(pesquisa.getCampo(), pesquisa.getValor());
				}else {
					where.ne(pesquisa.getCampo(), pesquisa.getValor());
				}
			}
			queryBuilder.orderBy(campo, order);
			PreparedQuery preparedQuery = queryBuilder.prepare();
			return this.daoImpl().query(preparedQuery);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List orderBy(String campo, boolean order){
		try {
			
			QueryBuilder<String, Object> queryBuilder =
					this.daoImpl().queryBuilder();
			queryBuilder.orderBy(campo, order);
			PreparedQuery preparedQuery = queryBuilder.prepare();
			return this.daoImpl().query(preparedQuery);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public boolean exists(String campo, Object valor){
		try {
			if(this.daoImpl().queryForEq(campo, valor).size() > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean hasElements() {

		return all().size() > 0;
	}

	public int count() {

		return all().size();
	}
	
}
