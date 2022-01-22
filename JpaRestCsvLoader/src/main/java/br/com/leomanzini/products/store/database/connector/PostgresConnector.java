package br.com.leomanzini.products.store.database.connector;

import java.io.Closeable;

import br.com.leomanzini.products.store.utils.PropertiesLoader;
import jakarta.persistence.EntityManager;

public class PostgresConnector implements DatabaseConnector, Closeable {
	
	private EntityManager entityManager;

	@Override
	public EntityManager createEntityManager() {
		if (isEntityManagerNull()) {
			entityManager = PropertiesLoader.createEntityManagerFactoryConfigured().createEntityManager();
		}
		return entityManager;
	}

	@Override
	public void close() {
		if (!isEntityManagerNull()) {
			entityManager.close();
		}
	}
	
	private boolean isEntityManagerNull() {
		if (entityManager == null) {
			return true;
		}
		return false;
	}
}
