package br.com.leomanzini.product.store.dao;

import br.com.leomanzini.product.store.dtos.StoreDto;

public interface StoreDao {
	
	public void persist(String path, StoreDto store) throws Exception;
}
