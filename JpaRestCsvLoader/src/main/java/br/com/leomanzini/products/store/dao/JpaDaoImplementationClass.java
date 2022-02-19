package br.com.leomanzini.products.store.dao;

import jakarta.persistence.EntityManager;
import lombok.Getter;

@Getter
public abstract class JpaDaoImplementationClass<T> implements GenericDaoInterface<T> {

	private static EntityManager entityManager;

	public JpaDaoImplementationClass(EntityManager entityManagerReceived) {
		entityManager = entityManagerReceived;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
