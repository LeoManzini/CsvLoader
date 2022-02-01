package br.com.leomanzini.products.store.dao.impl;

import java.util.List;

import br.com.leomanzini.products.store.dao.JpaDaoImplementationClass;
import br.com.leomanzini.products.store.model.entities.Store;
import br.com.leomanzini.products.store.utils.Queries;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class StoreDaoImpl extends JpaDaoImplementationClass<Store> {

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
		TypedQuery<Store> findStoreById = super.getEntityManager().createQuery(Queries.STORE_FIND_BY_ID.getQuery(),
				Store.class);
		findStoreById.setParameter("storeId", storeId);
		return findStoreById.getSingleResult();
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
	public boolean delete(Integer id) {
		Query deleteStore = super.getEntityManager().createQuery(Queries.STORE_DELETE.getQuery());

		super.getEntityManager().getTransaction().begin();

		deleteStore.setParameter("id", id);

		if (deleteStore.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Transactional
	public Store findByDocument(Integer storeDocument) {
		try {
			TypedQuery<Store> findStoreByDocument = super.getEntityManager()
					.createQuery(Queries.STORE_FIND_BY_DOCUMENT.getQuery(), Store.class);
			findStoreByDocument.setParameter("storeDocument", storeDocument);
			return findStoreByDocument.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	public boolean deleteByDocument(Integer storeDocument) {
		Query deleteStore = super.getEntityManager().createQuery(Queries.STORE_DELETE_BY_DOCUMENT.getQuery());

		super.getEntityManager().getTransaction().begin();

		deleteStore.setParameter("document", storeDocument);

		if (deleteStore.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}
}
