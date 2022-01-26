package br.com.leomanzini.products.store.dao;

import br.com.leomanzini.products.store.utils.PropertiesLoader;
import jakarta.persistence.EntityManager;
import lombok.Getter;

@Getter
public abstract class JpaDaoImplementationClass<T> implements GenericDaoInterface<T> {

	private EntityManager entityManager;

	public JpaDaoImplementationClass() {
		if (entityManager == null) {
			entityManager = PropertiesLoader.createEntityManagerFactoryConfigured().createEntityManager();
		}
	}
}
