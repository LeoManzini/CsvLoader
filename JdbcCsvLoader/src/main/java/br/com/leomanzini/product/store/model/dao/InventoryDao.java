package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Inventory;

public interface InventoryDao {

	void insert(Inventory inventory) throws Exception;
	void update (Inventory Inventory) throws Exception;
	void deleteById(Integer InventoryId) throws Exception;
	Inventory findById(Integer InventoryId) throws Exception;
	List<Inventory> findAll() throws Exception;
	
	boolean findStoreProduct(Integer productId, Integer storeId) throws Exception;
}
