package br.com.leomanzini.product.store.model.dao.impl;

import java.util.List;

import br.com.leomanzini.product.store.model.dao.InventoryDao;
import br.com.leomanzini.product.store.model.entities.Inventory;

public class InventoryDaoImplJdbc implements InventoryDao {

	@Override
	public void insert(Inventory inventory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Inventory Inventory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer InventoryId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Inventory findById(Integer InventoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inventory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findStoreProduct(Integer productId, Integer storeId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
