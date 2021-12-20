package br.com.leomanzini.product.store.model.dao;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.model.dao.impl.InventoryDaoImplJdbc;
import br.com.leomanzini.product.store.model.dao.impl.ProductDaoImplJdbc;
import br.com.leomanzini.product.store.model.dao.impl.StoreDaoImplJdbc;

public abstract class DaoFactory {

	@SuppressWarnings("resource")
	public static StoreDao createStoreDao(String propertiesPath) {
		return new StoreDaoImplJdbc(new PostgresConnector().startDatabaseConnection(propertiesPath));
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
