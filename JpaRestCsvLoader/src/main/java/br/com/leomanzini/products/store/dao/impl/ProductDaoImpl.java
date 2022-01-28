package br.com.leomanzini.products.store.dao.impl;

import java.util.List;

import br.com.leomanzini.products.store.dao.JpaDaoImplementationClass;
import br.com.leomanzini.products.store.model.entities.Product;
import br.com.leomanzini.products.store.utils.Queries;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class ProductDaoImpl extends JpaDaoImplementationClass<Product> {

	@Override
	@Transactional
	public List<Product> findAll() {
		TypedQuery<Product> findAllProductsQuery = super.getEntityManager().createQuery(Queries.PRODUCT_FIND_ALL.getQuery(),
				Product.class);
		return findAllProductsQuery.getResultList();
	}

	@Override
	@Transactional
	public Product findById(Integer id) {
		TypedQuery<Product> findProductsById = super.getEntityManager().createQuery(Queries.PRODUCT_FIND_BY_ID.getQuery(),
				Product.class);
		findProductsById.setParameter("productId", id);
		return findProductsById.getSingleResult();
	}

	@Override
	@Transactional
	public boolean insert(Product insertObject) {
		Query insertProducts = super.getEntityManager().createNativeQuery(Queries.PRODUCT_INSERT.getQuery());

		super.getEntityManager().getTransaction().begin();

		insertProducts.setParameter("name", insertObject.getName());
		insertProducts.setParameter("serial", insertObject.getSerial());

		if (insertProducts.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Override
	@Transactional
	public boolean update(Product updatableObject) {
		Query updateProduct = super.getEntityManager().createNativeQuery(Queries.PRODUCT_UPDATE.getQuery());

		super.getEntityManager().getTransaction().begin();

		updateProduct.setParameter("name", updatableObject.getName());
		updateProduct.setParameter("id", updatableObject.getId());

		if (updateProduct.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Override
	@Transactional
	public boolean delete(Integer id) {
		Query deleteProduct = super.getEntityManager().createQuery(Queries.PRODUCT_DELETE.getQuery());

		super.getEntityManager().getTransaction().begin();

		deleteProduct.setParameter("id", id);

		if (deleteProduct.executeUpdate() != 1) {
			return true;
		} else {
			super.getEntityManager().getTransaction().commit();
			return false;
		}
	}
}
