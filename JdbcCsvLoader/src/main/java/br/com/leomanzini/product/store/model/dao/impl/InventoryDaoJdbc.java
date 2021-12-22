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
import br.com.leomanzini.product.store.exceptions.InventoryDaoException;
import br.com.leomanzini.product.store.model.dao.InventoryDao;
import br.com.leomanzini.product.store.model.entities.Inventory;

public class InventoryDaoJdbc implements InventoryDao {

	private static final Logger log = LogManager.getLogger(InventoryDaoJdbc.class);

	private Connection conn;

	public InventoryDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Inventory inventory) throws InventoryDaoException {
		try (PreparedStatement insertInventory = conn.prepareStatement(Queries.INSERT_INVENTORY.getQuery())) {

			if (findAtDatabase(inventory.getProductSerie(), inventory.getStoreDocument())) {
				log.info("The above product was found with serie " + inventory.getProductSerie()
						+ ", please fix the current document");
				log.info("Fixing document error and updating inventory for this product");
				update(inventory);
			} else {
				insertInventory.setInt(1, inventory.getProductSerie());
				insertInventory.setInt(2, inventory.getProductId());
				insertInventory.setInt(3, inventory.getStoreDocument());
				insertInventory.setInt(4, inventory.getAmount());
				insertInventory.setBigDecimal(5, inventory.getPrice());

				if (!(insertInventory.executeUpdate() > 0)) {
					throw new Exception("Inventory insert operation error");
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_INSERT_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_INSERT_ERROR);
		}
	}

	@Override
	public void update(Inventory inventory) throws InventoryDaoException {
		try (PreparedStatement updateInventory = conn.prepareStatement(Queries.UPDATE_INVENTORY.getQuery())) {

			updateInventory.setInt(1, inventory.getAmount());
			updateInventory.setBigDecimal(2, inventory.getPrice());
			updateInventory.setInt(3, inventory.getProductSerie());
			updateInventory.setInt(4, inventory.getStoreDocument());

			if (!(updateInventory.executeUpdate() > 0)) {
				throw new Exception("Inventory update operation failed");
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_INSERT_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_INSERT_ERROR);
		}
	}

	@Override
	public void deleteBySerie(Integer productSerie, Integer storeId) throws InventoryDaoException {
		try (PreparedStatement deleteInventory = conn.prepareStatement(Queries.DELETE_INVENTORY.getQuery())) {

			deleteInventory.setInt(1, productSerie);
			deleteInventory.setInt(2, storeId);

			if (!(deleteInventory.executeUpdate() == 1)) {
				throw new Exception("Inventory delete operation failed");
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_DELETE_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_DELETE_ERROR);
		}
	}

	@Override
	public Inventory findById(Integer productId, Integer storeId) throws InventoryDaoException {
		ResultSet inventoryResultSet = null;

		try (PreparedStatement findInventoryById = conn.prepareStatement(Queries.FIND_INVENTORY_BY_ID.getQuery())) {

			findInventoryById.setInt(1, productId);
			findInventoryById.setInt(2, storeId);

			inventoryResultSet = findInventoryById.executeQuery();

			if (inventoryResultSet.next()) {
				Inventory inventory = instanciateInventory(inventoryResultSet);
				return inventory;
			} else {
				throw new Exception("Inventory consult operation failed");
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_INSERT_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_INSERT_ERROR);
		}
	}

	@Override
	public List<Inventory> findAll() throws InventoryDaoException, SQLException {
		ResultSet inventoryResultSet = null;
		List<Inventory> inventoryResultList = new ArrayList<>();

		try (PreparedStatement findInventories = conn.prepareStatement(Queries.FIND_ALL_INVENTORY.getQuery())) {

			inventoryResultSet = findInventories.executeQuery();

			while (inventoryResultSet.next()) {
				Inventory inventory = instanciateInventory(inventoryResultSet);
				inventoryResultList.add(inventory);
			}

			return inventoryResultList;

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_FIND_ALL_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_DELETE_ERROR);
		} finally {
			inventoryResultSet.close();
		}
	}

	@Override
	public boolean findAtDatabase(Integer productSerie, Integer storeId) throws InventoryDaoException, SQLException {
		ResultSet inventoryResultSet = null;

		try (PreparedStatement findStoreProduct = conn.prepareStatement(Queries.FIND_INVENTORY_BY_SERIE.getQuery())) {

			findStoreProduct.setInt(1, productSerie);
			findStoreProduct.setInt(2, storeId);

			inventoryResultSet = findStoreProduct.executeQuery();

			if (inventoryResultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_FIND_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new InventoryDaoException(ErrorMessages.INVENTORY_FIND_ERROR);
		} finally {
			inventoryResultSet.close();
		}
	}

	private Inventory instanciateInventory(ResultSet inventoryResultSet) throws SQLException {
		Inventory inventory = new Inventory();
		inventory.setId(inventoryResultSet.getInt("id"));
		inventory.setProductSerie(inventoryResultSet.getInt("product_serie"));
		inventory.setProductId(inventoryResultSet.getInt("product_id"));
		inventory.setStoreDocument(inventoryResultSet.getInt("store_id"));
		inventory.setAmount(inventoryResultSet.getInt("amount"));
		inventory.setPrice(inventoryResultSet.getBigDecimal("price"));

		return inventory;
	}
}
