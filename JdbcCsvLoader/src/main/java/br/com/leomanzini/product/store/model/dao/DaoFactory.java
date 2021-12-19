package br.com.leomanzini.product.store.model.dao;

import br.com.leomanzini.product.store.model.dao.impl.InventoryDaoImplJdbc;
import br.com.leomanzini.product.store.model.dao.impl.ProductDaoImplJdbc;
import br.com.leomanzini.product.store.model.dao.impl.StoreDaoImplJdbc;

public abstract class DaoFactory {

	public static StoreDao createStoreDao() {
		return new StoreDaoImplJdbc();
	}

	public static ProductDao createProductDao() {
		return new ProductDaoImplJdbc();
	}

	public static InventoryDao createInventoryDao() {
		return new InventoryDaoImplJdbc();
	}
}
