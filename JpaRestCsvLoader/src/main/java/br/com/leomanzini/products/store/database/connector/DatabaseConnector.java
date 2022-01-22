package br.com.leomanzini.products.store.database.connector;

import jakarta.persistence.EntityManager;

public interface DatabaseConnector {

	public EntityManager createEntityManager();
	public void close();
}
