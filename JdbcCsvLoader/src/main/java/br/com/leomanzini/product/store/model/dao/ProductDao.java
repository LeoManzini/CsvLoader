package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Product;

public interface ProductDao {
	
	void insert(Product product) throws Exception;
	void update (Product product) throws Exception;
	void deleteById(Integer productId) throws Exception;
	Product findById(Integer productId) throws Exception;
	List<Product> findAll() throws Exception;
	
	boolean findAtDatabase(Product product) throws Exception;
}
