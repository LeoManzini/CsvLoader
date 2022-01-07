package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Inventory;

public interface InventoryDao {

	void insert(Inventory inventory) throws Exception;
	void update (Inventory inventory) throws Exception;
	void deleteBySerie(Integer productSerie, Integer storeDocument) throws Exception;
	Inventory findById(Integer productId, Integer storeDocument) throws Exception;
	List<Inventory> findAll() throws Exception;
	
	boolean findAtDatabase(Integer productSerie, Integer storeDocument) throws Exception;
}
