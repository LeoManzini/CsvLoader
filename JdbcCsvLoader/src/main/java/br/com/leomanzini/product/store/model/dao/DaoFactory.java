package br.com.leomanzini.product.store.model.dao;

import java.sql.Connection;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.model.dao.impl.InventoryDaoImplJdbc;
import br.com.leomanzini.product.store.model.dao.impl.ProductDaoImplJdbc;
import br.com.leomanzini.product.store.model.dao.impl.StoreDaoImplJdbc;

public class DaoFactory {
	
	private Connection databaseConnection;
	
	@SuppressWarnings("resource")
	public DaoFactory (String propertiesPath) {
		databaseConnection = new PostgresConnector().startDatabaseConnection(propertiesPath);
	}

	public StoreDao createStoreDao() {
		return new StoreDaoImplJdbc(databaseConnection);
	}

	public ProductDao createProductDao() {
		return new ProductDaoImplJdbc(databaseConnection);
	}

	public InventoryDao createInventoryDao() {
		return new InventoryDaoImplJdbc(databaseConnection);
	}
}
