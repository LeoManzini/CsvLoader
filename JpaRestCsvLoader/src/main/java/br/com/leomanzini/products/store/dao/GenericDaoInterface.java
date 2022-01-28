package br.com.leomanzini.products.store.dao;

import java.util.List;

public interface GenericDaoInterface<T> {

	List<T> findAll();
	T findById(Long id);
	boolean insert(T insertObject);
	boolean update(T updatableObject);
	boolean delete(Long id);
}
