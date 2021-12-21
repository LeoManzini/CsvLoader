package br.com.leomanzini.product.store.model.dao;

import java.sql.Connection;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.model.dao.impl.InventoryDaoJdbc;
import br.com.leomanzini.product.store.model.dao.impl.ProductDaoJdbc;
import br.com.leomanzini.product.store.model.dao.impl.StoreDaoJdbc;

public class DaoFactory {
	
	private Connection databaseConnection;
	
	@SuppressWarnings("resource")
	public DaoFactory (String propertiesPath) {
		databaseConnection = new PostgresConnector().startDatabaseConnection(propertiesPath);
	}

	public StoreDao createStoreDao() {
		return new StoreDaoJdbc(databaseConnection);
	}

	public ProductDao createProductDao() {
		return new ProductDaoJdbc(databaseConnection);
	}

	public InventoryDao createInventoryDao() {
		return new InventoryDaoJdbc(databaseConnection);
	}
}
