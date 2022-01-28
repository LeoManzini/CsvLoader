package br.com.leomanzini.products.store.dao;

import java.util.List;

public interface GenericDaoInterface<T> {

	List<T> findAll();
	T findById(Integer id);
	boolean insert(T insertObject);
	boolean update(T updatableObject);
	boolean delete(Integer id);
}
