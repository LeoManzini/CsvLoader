package br.com.leomanzini.product.store.model.dao;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.model.dao.impl.InventoryDaoImplJdbc;
import br.com.leomanzini.product.store.model.dao.impl.ProductDaoImplJdbc;

public abstract class DaoFactory {

	public static StoreDao createStoreDao() {
		return null;
	}

	@SuppressWarnings("resource")
	public static ProductDao createProductDao(String propertiesPath) {
		return new ProductDaoImplJdbc(new PostgresConnector().startDatabaseConnection(propertiesPath));
	}

	@SuppressWarnings("resource")
	public static InventoryDao createInventoryDao(String propertiesPath) {
		return new InventoryDaoImplJdbc(new PostgresConnector().startDatabaseConnection(propertiesPath));
	}
}
