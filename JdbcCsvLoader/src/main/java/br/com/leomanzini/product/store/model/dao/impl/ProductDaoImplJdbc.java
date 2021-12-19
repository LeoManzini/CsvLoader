package br.com.leomanzini.product.store.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.leomanzini.product.store.enums.Queries;
import br.com.leomanzini.product.store.model.dao.ProductDao;
import br.com.leomanzini.product.store.model.entities.Product;

public class ProductDaoImplJdbc implements ProductDao {

	private Connection con;

	public ProductDaoImplJdbc(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product findById(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findAtDatabase(Integer productId) throws Exception {
		ResultSet productResultSet;

		try (PreparedStatement preparedStatementFindProductId = con
				.prepareStatement(Queries.CHECK_EXISTING_PRODUCT_DATABASE.getQuery())) {

			preparedStatementFindProductId.setInt(1, productId);

			productResultSet = preparedStatementFindProductId.executeQuery();

			if (productResultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new Exception();
		}
	}
}
