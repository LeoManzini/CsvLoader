package br.com.leomanzini.products.store.dao;

import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.utils.PropertiesLoader;
import jakarta.persistence.EntityManager;

public abstract class DaoFactory {

	private static EntityManager entityManager;
	
	private static void startEntityManager() {
		if (entityManager == null) {
			entityManager = PropertiesLoader.createEntityManagerFactoryConfigured().createEntityManager();
		}
	}
	
	public static StoreDaoImpl getStoreDaoImplementation() {
		startEntityManager();
		return new StoreDaoImpl(entityManager);
	}
	
	public static ProductDaoImpl getProductDaoImplementation() {
		startEntityManager();
		return new ProductDaoImpl(entityManager);
	}
	
	public static InventoryDaoImpl getInventoryDaoImplementation() {
		startEntityManager();
		return new InventoryDaoImpl(entityManager);
	}
}
