package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Store;

public interface StoreDao {
	
	void insert(Store store) throws Exception;
	void update (Store store) throws Exception;
	void deleteById(Integer storeId) throws Exception;
	Store findById(Integer storeId) throws Exception;
	List<Store> findAll() throws Exception;
}
