package br.com.leomanzini.products.store.dao;

import br.com.leomanzini.products.store.database.connector.PostgresConnector;
import jakarta.persistence.EntityManager;
import lombok.Data;

@Data
public abstract class JpaGenericDaoMethods<T> implements GenericDaoInterface<T> {

	private EntityManager manager;
	
	@SuppressWarnings("resource")
	protected JpaGenericDaoMethods () {
		manager = new PostgresConnector().createEntityManager();
	}
	
	
}
