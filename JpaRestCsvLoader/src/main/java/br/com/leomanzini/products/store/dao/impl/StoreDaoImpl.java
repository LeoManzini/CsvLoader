package br.com.leomanzini.products.store.dao.impl;

import java.util.List;

import br.com.leomanzini.products.store.dao.JpaDaoImplementationClass;
import br.com.leomanzini.products.store.dto.ResponseObjectDto;
import br.com.leomanzini.products.store.model.entities.Store;
import br.com.leomanzini.products.store.utils.Queries;
import jakarta.persistence.TypedQuery;

public class StoreDaoImpl extends JpaDaoImplementationClass<Store> {

	@Override
	public List<Store> findAll() {
		TypedQuery<Store> findAllStoresQuery = super.getEntityManager().createQuery(Queries.STORE_FIND_ALL.getQuery(),
				Store.class);
		return findAllStoresQuery.getResultList();
	}

	@Override
	public Store findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseObjectDto insert(Store insertObject) {
		// TODO Auto-generated method stub
		return null;
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
