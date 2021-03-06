package br.com.leomanzini.products.store.dao.impl;

import java.util.List;

import br.com.leomanzini.products.store.dao.JpaDaoImplementationClass;
import br.com.leomanzini.products.store.entities.Store;
import br.com.leomanzini.products.store.utils.Queries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class StoreDaoImpl extends JpaDaoImplementationClass<Store> {

	public StoreDaoImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	@Transactional
	public List<Store> findAll() {
		TypedQuery<Store> findAllStoresQuery = super.getEntityManager().createQuery(Queries.STORE_FIND_ALL.getQuery(),
				Store.class);
		return findAllStoresQuery.getResultList();
	}

	@Override
	@Transactional
	public Store findById(Integer storeId) {
		try {
			TypedQuery<Store> findStoreByDocument = super.getEntityManager()
					.createQuery(Queries.STORE_FIND_BY_DOCUMENT.getQuery(), Store.class);
			findStoreByDocument.setParameter("storeDocument", storeId);
			return findStoreByDocument.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public boolean insert(Store insertObject) {
		Query insertStore = super.getEntityManager().createNativeQuery(Queries.STORE_INSERT.getQuery());

		super.getEntityManager().getTransaction().begin();

		insertStore.setParameter("name", insertObject.getName());
		insertStore.setParameter("document", insertObject.getDocument());

		if (insertStore.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Override
	@Transactional
	public boolean update(Store updatableObject) {
		Query updateStore = super.getEntityManager().createNativeQuery(Queries.STORE_UPDATE.getQuery());

		super.getEntityManager().getTransaction().begin();

		updateStore.setParameter("name", updatableObject.getName());
		updateStore.setParameter("document", updatableObject.getDocument());

		if (updateStore.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Override
	@Transactional
	public boolean delete(Integer document) {
		Query deleteStore = super.getEntityManager().createQuery(Queries.STORE_DELETE_BY_DOCUMENT.getQuery());

		super.getEntityManager().getTransaction().begin();

		deleteStore.setParameter("document", document);

		if (deleteStore.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Transactional
	public boolean findStore(Integer document) {
		try {
			TypedQuery<Store> findStoreByDocument = super.getEntityManager()
					.createQuery(Queries.STORE_FIND_BY_DOCUMENT.getQuery(), Store.class);
			findStoreByDocument.setParameter("storeDocument", document);
			findStoreByDocument.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
