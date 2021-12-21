package br.com.leomanzini.product.store.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.enums.Queries;
import br.com.leomanzini.product.store.exceptions.ProductDaoException;
import br.com.leomanzini.product.store.model.dao.ProductDao;
import br.com.leomanzini.product.store.model.entities.Product;

public class ProductDaoImplJdbc implements ProductDao {
	
	private static final Logger log = LogManager.getLogger(ProductDaoImplJdbc.class);

	private Connection con;

	public ProductDaoImplJdbc(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Product product) throws ProductDaoException {
		try (PreparedStatement insertProduct = con.prepareStatement(Queries.INSERT_PRODUCT.getQuery(),
				new String[] { "id" })) {

			insertProduct.setString(1, product.getName());

			if (insertProduct.executeUpdate() > 0) {
				ResultSet productIndex = insertProduct.getGeneratedKeys();

				if (productIndex.next()) {
					product.setId(productIndex.getInt(1));
					product.getInventory().setProductId(product.getId());
				}
			} else {
				throw new Exception("Product insert operation failed");
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_INSERT_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_INSERT_ERROR);
		}
	}

	@Override
	public void update(Product product) throws ProductDaoException {
		try (PreparedStatement updateProduct = con.prepareStatement(Queries.UPDATE_PRODUCT.getQuery())) {

			updateProduct.setString(1, product.getName());
			updateProduct.setInt(2, product.getId());

			if (!(updateProduct.executeUpdate() == 1)) {
				throw new Exception("Product update operation failed");
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_UPDATE_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_UPDATE_ERROR);
		}
	}

	@Override
	public void deleteById(Integer productId) throws ProductDaoException {
		try (PreparedStatement deleteProduct = con.prepareStatement(Queries.DELETE_PRODUCT.getQuery())) {

			deleteProduct.setInt(1, productId);

			if (!(deleteProduct.executeUpdate() == 1)) {
				throw new Exception("Product delete operation failed");
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_DELETE_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_DELETE_ERROR);
		}
	}

	@Override
	public Product findById(Integer productId) throws ProductDaoException, SQLException {
		ResultSet productResultSet = null;

		try (PreparedStatement findProductId = con.prepareStatement(Queries.FIND_PRODUCT.getQuery())) {

			findProductId.setInt(1, productId);

			productResultSet = findProductId.executeQuery();

			if (productResultSet.next()) {
				Product product = new Product();
				product.setId(productResultSet.getInt("id"));
				product.setName(productResultSet.getString("name"));

				return product;
			} else {
				throw new Exception("Product findById operation failed");
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_FIND_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_FIND_ERROR);
		} finally {
			productResultSet.close();
		}
	}

	@Override
	public List<Product> findAll() throws ProductDaoException, SQLException {
		ResultSet productResultSet = null;
		List<Product> resultList = new ArrayList<>();
		
		try (PreparedStatement findProducts = con.prepareStatement(Queries.FIND_ALL_PRODUCTS.getQuery())) {

			productResultSet = findProducts.executeQuery();

			while (productResultSet.next()) {
				Product product = new Product();
				product.setId(productResultSet.getInt("id"));
				product.setName(productResultSet.getString("name"));
				
				resultList.add(product);
			}
			return resultList;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_FIND_ALL_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_FIND_ALL_ERROR);
		} finally {
			productResultSet.close();
		}
	}

	@Override
	public boolean findAtDatabase(Product product) throws ProductDaoException, SQLException {
		ResultSet productResultSet = null;

		try (PreparedStatement preparedStatementFindProductId = con.prepareStatement(Queries.FIND_PRODUCT.getQuery(),
				new String[] { "id" })) {

			preparedStatementFindProductId.setInt(1, product.getId());
			preparedStatementFindProductId.setString(2, product.getName());

			productResultSet = preparedStatementFindProductId.executeQuery();

			if (productResultSet.next()) {
				product.setId(productResultSet.getInt(1));
				product.getInventory().setProductId(product.getId());

				return true;
			}
			return false;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_FIND_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductDaoException(ErrorMessages.PRODUCT_FIND_ALL_ERROR);
		} finally {
			productResultSet.close();
		}
	}
}
