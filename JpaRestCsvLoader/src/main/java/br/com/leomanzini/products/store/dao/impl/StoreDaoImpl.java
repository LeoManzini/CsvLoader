package br.com.leomanzini.products.store.dao.impl;

import java.util.List;

import br.com.leomanzini.products.store.dao.JpaDaoImplementationClass;
import br.com.leomanzini.products.store.dto.ResponseObjectDto;
import br.com.leomanzini.products.store.model.entities.Store;
import br.com.leomanzini.products.store.utils.Queries;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class StoreDaoImpl extends JpaDaoImplementationClass<Store> {

	@Override
	public List<Store> findAll() {
		TypedQuery<Store> findAllStoresQuery = super.getEntityManager().createQuery(Queries.STORE_FIND_ALL.getQuery(),
				Store.class);
		return findAllStoresQuery.getResultList();
	}

	@Override
	public Store findById(Long id) {
		TypedQuery<Store> findStoreById = super.getEntityManager().createQuery(Queries.STORE_FIND_BY_ID.getQuery(), Store.class);
		findStoreById.setParameter("storeId", id);
		return findStoreById.getSingleResult();
	}

	@Override
	@Transactional
	public ResponseObjectDto insert(Store insertObject) {
		Query insertStore = super.getEntityManager().createNativeQuery(Queries.STORE_INSERT.getQuery());
		
		super.getEntityManager().getTransaction().begin();
		
		insertStore.setParameter("name", insertObject.getName());
		insertStore.setParameter("document", insertObject.getDocument());
		
		if(insertStore.executeUpdate() >= 1) {
			return ResponseObjectDto.builder().message("Deu errado...").build();
		} else {
			super.getEntityManager().getTransaction().commit();
			return ResponseObjectDto.builder().message("Deu certo!").build();
		}
	}

	@Override
	public ResponseObjectDto update(Store updatableObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseObjectDto delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
