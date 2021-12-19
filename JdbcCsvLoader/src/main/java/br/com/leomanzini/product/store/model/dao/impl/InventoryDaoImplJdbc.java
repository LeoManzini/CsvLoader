package br.com.leomanzini.product.store.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.enums.Queries;
import br.com.leomanzini.product.store.exceptions.InventoryDaoException;
import br.com.leomanzini.product.store.exceptions.ProductDaoException;
import br.com.leomanzini.product.store.model.dao.InventoryDao;
import br.com.leomanzini.product.store.model.entities.Inventory;

public class InventoryDaoImplJdbc implements InventoryDao {

	private static final Logger log = LogManager.getLogger(InventoryDaoImplJdbc.class);

	private Connection conn;

	public InventoryDaoImplJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Inventory inventory) throws InventoryDaoException {
		try (PreparedStatement insertInventory = conn.prepareStatement(Queries.INSERT_INVENTORY.getQuery())) {
 
			insertInventory.setInt(1, inventory.getProductId());
			insertInventory.setInt(2, inventory.getStoreId());
			insertInventory.setInt(3, inventory.getAmount());
			insertInventory.setBigDecimal(4, inventory.getPrice());
			
			if (!(insertInventory.executeUpdate() == 1)) {
				throw new Exception("Inventory insert operation error");
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
			updateInventory.setInt(3, inventory.getProductId());
			updateInventory.setInt(3, inventory.getStoreId());

			if (!(updateInventory.executeUpdate() == 1)) {
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
	public void deleteById(Integer inventoryId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Inventory findById(Integer inventoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inventory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findStoreProduct(Integer productId, Integer storeId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
