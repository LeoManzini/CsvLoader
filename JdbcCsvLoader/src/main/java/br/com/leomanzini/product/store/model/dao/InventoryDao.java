package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Inventory;

public interface InventoryDao {

	void insert(Inventory inventory);
	void update (Inventory Inventory);
	void deleteById(Integer InventoryId);
	Inventory findById(Integer InventoryId);
	List<Inventory> findAll();
}
