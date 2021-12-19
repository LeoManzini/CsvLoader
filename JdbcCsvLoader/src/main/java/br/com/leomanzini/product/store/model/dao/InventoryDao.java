package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Inventory;

public interface InventoryDao {

	void insert(Inventory inventory) throws Exception;
	void update (Inventory inventory) throws Exception;
	void deleteById(Integer productId, Integer storeId) throws Exception;
	Inventory findById(Integer productId, Integer storeId) throws Exception;
	List<Inventory> findAll() throws Exception;
	
	boolean findAtDatabase(Integer productId, Integer storeId) throws Exception;
}
