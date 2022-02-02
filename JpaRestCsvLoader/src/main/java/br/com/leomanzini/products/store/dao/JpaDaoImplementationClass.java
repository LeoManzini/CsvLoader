package br.com.leomanzini.products.store.dao;

import jakarta.persistence.EntityManager;
import lombok.Getter;

@Getter
public abstract class JpaDaoImplementationClass<T> implements GenericDaoInterface<T> {

	private EntityManager entityManager;

	public JpaDaoImplementationClass(EntityManager entityManager) {
		if (entityManager == null) {
			this.entityManager = entityManager;
		}
	}
}
