package br.com.leomanzini.product.store.model.dao;

import br.com.leomanzini.product.store.model.entities.Store;

public interface StoreDao {
	
	public void persist(String path, Store store) throws Exception;
}
