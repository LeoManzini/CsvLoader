package br.com.leomanzini.product.store.model.dao;

import java.sql.Connection;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.model.dao.impl.InventoryDaoJdbc;
import br.com.leomanzini.product.store.model.dao.impl.ProductDaoJdbc;
import br.com.leomanzini.product.store.model.dao.impl.StoreDaoJdbc;

public abstract class DaoFactory {
	
	private static Connection databaseConnection;

	public static StoreDao createStoreDao() {
		return new StoreDaoJdbc(databaseConnection);
	}

	public static ProductDao createProductDao() {
		return new ProductDaoJdbc(databaseConnection);
	}

	public static InventoryDao createInventoryDao() {
		return new InventoryDaoJdbc(databaseConnection);
	}
	
	@SuppressWarnings("resource")
	public static void startConnection (String propertiesPath) {
		databaseConnection = new PostgresConnector().startDatabaseConnection(propertiesPath);
	}
}
