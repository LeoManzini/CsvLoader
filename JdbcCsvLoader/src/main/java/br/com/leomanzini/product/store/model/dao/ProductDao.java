package br.com.leomanzini.product.store.model.dao;

import java.util.List;

import br.com.leomanzini.product.store.model.entities.Product;

public interface ProductDao {
	
	void insert(Product product);
	void update (Product product);
	void deleteById(Integer productId);
	Product findById(Integer productId);
	List<Product> findAll();
	
	boolean findAtDatabase(Integer productId) throws Exception;
}
