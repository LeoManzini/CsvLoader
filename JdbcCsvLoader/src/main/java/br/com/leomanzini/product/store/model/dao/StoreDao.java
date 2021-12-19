package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Store;

public interface StoreDao {
	
	void insert(Store store);
	void update (Store store);
	void deleteById(Integer storeId);
	Store findById(Integer storeId);
	List<Store> findAll();
}
