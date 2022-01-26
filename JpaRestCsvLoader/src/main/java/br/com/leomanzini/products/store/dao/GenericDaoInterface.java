package br.com.leomanzini.products.store.dao;

import java.util.List;

import br.com.leomanzini.products.store.dto.ResponseObjectDto;

public interface GenericDaoInterface<T> {

	List<T> findAll();
	T findById(Long id);
	ResponseObjectDto insert(T insertObject);
	ResponseObjectDto update(T updatableObject);
	ResponseObjectDto delete(Long id);
}
