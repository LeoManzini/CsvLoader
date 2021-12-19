package br.com.leomanzini.product.store.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		try (PreparedStatement insertProduct = con.prepareStatement(Queries.INSERT_PRODUCT.getQuery())) {

			insertProduct.setString(1, product.getName());

			if (!(insertProduct.executeUpdate() == 1)) {

			}

		} catch (SQLException e) {

		}
	}

	@Override
	public void update(Product product) {
		try (PreparedStatement insertProduct = con.prepareStatement(Queries.UPDATE_PRODUCT.getQuery())) {

			insertProduct.setString(1, product.getName());
			insertProduct.setInt(2, product.getId());

			if (!(insertProduct.executeUpdate() == 1)) {

			}

		} catch (SQLException e) {

		}
	}

	@Override
	public void deleteById(Integer productId) {
		try (PreparedStatement insertProduct = con.prepareStatement(Queries.DELETE_PRODUCT.getQuery())) {

			insertProduct.setInt(1, productId);

			if (!(insertProduct.executeUpdate() == 1)) {

			}

		} catch (SQLException e) {

		}
	}

	@Override
	public Product findById(Integer productId) throws Exception {
		ResultSet productResultSet = null;

		try (PreparedStatement preparedStatementFindProductId = con.prepareStatement(Queries.FIND_PRODUCT.getQuery())) {

			preparedStatementFindProductId.setInt(1, productId);

			productResultSet = preparedStatementFindProductId.executeQuery();

			if (productResultSet.next()) {
				Product product = new Product();
				product.setId(productResultSet.getInt("id"));
				product.setName(productResultSet.getString("name"));

				return product;
			}
			return null;
		} catch (SQLException e) {
			throw new Exception();
		} finally {
			productResultSet.close();
		}
	}

	@Override
	public List<Product> findAll() throws Exception {
		ResultSet productResultSet = null;
		List<Product> resultList = new ArrayList<>();
		
		try (PreparedStatement preparedStatementFindProductId = con.prepareStatement(Queries.FIND_ALL_PRODUCTS.getQuery())) {

			productResultSet = preparedStatementFindProductId.executeQuery();

			while (productResultSet.next()) {
				Product product = new Product();
				product.setId(productResultSet.getInt("id"));
				product.setName(productResultSet.getString("name"));
				
				resultList.add(product);
			}
			return resultList;
		} catch (SQLException e) {
			throw new Exception();
		} finally {
			productResultSet.close();
		}
	}

	@Override
	public boolean findAtDatabase(Integer productId) throws Exception {
		ResultSet productResultSet = null;

		try (PreparedStatement preparedStatementFindProductId = con.prepareStatement(Queries.FIND_PRODUCT.getQuery())) {

			preparedStatementFindProductId.setInt(1, productId);

			productResultSet = preparedStatementFindProductId.executeQuery();

			if (productResultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new Exception();
		} finally {
			productResultSet.close();
		}
	}
}
