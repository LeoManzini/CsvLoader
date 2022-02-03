package br.com.leomanzini.product.store.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.enums.Queries;
import br.com.leomanzini.product.store.exceptions.StoreDaoException;
import br.com.leomanzini.product.store.model.dao.StoreDao;
import br.com.leomanzini.product.store.model.entities.Inventory;
import br.com.leomanzini.product.store.model.entities.Product;
import br.com.leomanzini.product.store.model.entities.Store;

public class StoreDaoJdbc implements StoreDao {

	private static final Logger log = LogManager.getLogger(StoreDaoJdbc.class);

	private Connection conn;

	public StoreDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Store store) throws StoreDaoException {
		try (PreparedStatement insertStore = conn.prepareStatement(Queries.INSERT_STORE.getQuery())) {

			insertStore.setString(1, store.getName());
			insertStore.setInt(2, store.getDocument());

			if (insertStore.executeUpdate() > 0) {
				ResultSet storeIndex = insertStore.getGeneratedKeys();

				if (storeIndex.next()) {
					store.setId(storeIndex.getInt(1));
				}
			} else {
				throw new Exception("Store insert operation failed");
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_INSERT_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_INSERT_ERROR);
		}
	}

	@Override
	public void update(Store store) throws StoreDaoException {
		try (PreparedStatement insertStore = conn.prepareStatement(Queries.UPDATE_STORE.getQuery())) {

			insertStore.setString(1, store.getName());
			insertStore.setInt(2, store.getDocument());

			if (!(insertStore.executeUpdate() == 1)) {
				throw new Exception("Store update operation failed");
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_INSERT_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_INSERT_ERROR);
		}
	}

	@Override
	public void deleteById(Integer storeId) throws StoreDaoException {
		try (PreparedStatement insertStore = conn.prepareStatement(Queries.DELETE_STORE.getQuery())) {

			insertStore.setInt(1, storeId);

			if (!(insertStore.executeUpdate() == 1)) {
				throw new Exception("Store delete operation failed");
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_INSERT_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_INSERT_ERROR);
		}
	}

	@Override
	public Store findById(Integer storeId) throws StoreDaoException, SQLException {
		ResultSet storeResultSet = null;

		try (PreparedStatement findStoreById = conn.prepareStatement(Queries.FIND_STORE_BY_ID.getQuery())) {

			findStoreById.setInt(1, storeId);

			storeResultSet = findStoreById.executeQuery();

			if (storeResultSet.next()) {
				Store store = instanciateStore(storeResultSet);
				return store;
			} else {
				throw new Exception("Find store operation failed");
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ERROR);
		} finally {
			storeResultSet.close();
		}
	}

	@Override
	public List<Store> findAll() throws StoreDaoException, SQLException {
		ResultSet storeResultSet = null;
		List<Store> storeResultList = new ArrayList<>();

		try (PreparedStatement findAllStore = conn.prepareStatement(Queries.FIND_ALL_STORE.getQuery())) {

			storeResultSet = findAllStore.executeQuery();

			while (storeResultSet.next()) {
				Store store = instanciateStore(storeResultSet);
				storeResultList.add(store);
			}
			return storeResultList;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ALL_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ALL_ERROR);
		} finally {
			storeResultSet.close();
		}
	}

	@Override
	public boolean findAtDatabase(Integer storeDocument) throws Exception {
		ResultSet storeResultSet = null;

		try (PreparedStatement findStoreById = conn.prepareStatement(Queries.FIND_STORE_BY_DOCUMENT.getQuery())) {

			findStoreById.setInt(1, storeDocument);

			storeResultSet = findStoreById.executeQuery();

			if (storeResultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ERROR);
		} finally {
			storeResultSet.close();
		}
	}

	@Override
	public Store findStoreWithProducts(Integer storeDocument) throws Exception {
		ResultSet storeResultSet = null;

		try (PreparedStatement findStoreById = conn.prepareStatement(Queries.FIND_FULL_STORE_BY_DOCUMENT.getQuery(),
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			findStoreById.setInt(1, storeDocument);

			storeResultSet = findStoreById.executeQuery();

			if (storeResultSet.next()) {
				Store store = instanciateFullStore(storeResultSet);
				return store;
			} else {
				throw new Exception("Find store operation failed");
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ERROR);
		} finally {
			storeResultSet.close();
		}
	}

	@Override
	public List<Store> findAllStoresWithProducts() throws Exception {
		ResultSet storeResultSet = null;

		try (PreparedStatement findAllStore = conn.prepareStatement(Queries.FIND_FULL_STORES.getQuery(),
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			storeResultSet = findAllStore.executeQuery();

			return instanciateFullStores(storeResultSet);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ALL_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.STORE_FIND_ALL_ERROR);
		} finally {
			storeResultSet.close();
		}
	}

	private Store instanciateStore(ResultSet storeResultSet) throws SQLException {
		Store store = new Store();
		store.setId(storeResultSet.getInt("store_id"));
		store.setName(storeResultSet.getString("store_name"));
		store.setDocument(storeResultSet.getInt("store_document"));
		store.setProducts(new ArrayList<>());

		return store;
	}

	private Inventory instanciateInventory(ResultSet inventoryResultSet) throws SQLException {
		Inventory inventory = new Inventory();
		inventory.setId(inventoryResultSet.getInt("inventory_id"));
		inventory.setProductSerie(inventoryResultSet.getInt("product_serial"));
		inventory.setStoreDocument(inventoryResultSet.getInt("store_document"));
		inventory.setAmount(inventoryResultSet.getInt("amount"));
		inventory.setPrice(inventoryResultSet.getBigDecimal("price"));

		return inventory;
	}

	private Product instanciateProduct(ResultSet productResultSet, Inventory inventory) throws SQLException {
		Product product = new Product();
		product.setId(productResultSet.getInt("product_id"));
		product.setName(productResultSet.getString("product_name"));
		product.setInventory(inventory);
		return product;
	}

	private Store instanciateFullStore(ResultSet storeResultSet) throws SQLException {
		Store store = instanciateStore(storeResultSet);

		storeResultSet.beforeFirst();

		while (storeResultSet.next()) {
			Inventory inventory = instanciateInventory(storeResultSet);
			Product product = instanciateProduct(storeResultSet, inventory);
			store.getProducts().add(product);
		}
		return store;
	}

	private List<Store> instanciateFullStores(ResultSet storeResultSet) throws SQLException {
		List<Store> storeResultList = new ArrayList<>();
		Map<Integer, Store> mapStores = new HashMap<>();

		while (storeResultSet.next()) {
			Store store = mapStores.get(storeResultSet.getInt("store_document"));

			if (store == null) {
				store = instanciateStore(storeResultSet);
				Inventory inventory = instanciateInventory(storeResultSet);
				Product product = instanciateProduct(storeResultSet, inventory);
				store.getProducts().add(product);

				mapStores.put(storeResultSet.getInt("store_document"), store);
				storeResultList.add(store);
			} else {
				Inventory inventory = instanciateInventory(storeResultSet);
				Product product = instanciateProduct(storeResultSet, inventory);
				store.getProducts().add(product);
			}
		}
		return storeResultList;
	}

	/**
	 * Old versions, using procedures and other using a routine at java and doing
	 * all the database flow at this class
	 */
//	String callableSql = "{call products_test(?, ?)}";
//
//	@Override
//	public void persist(String propertiesPath, StoreDto store) throws StoreDaoException {
//		try (PostgresConnector postgresConnector = new PostgresConnector();
//				Connection postgresConnection = postgresConnector.startDatabaseConnection(propertiesPath);
//				CallableStatement callableStatement = postgresConnection.prepareCall(callableSql)) {
//
//			callableStatement.setInt(1, store.getId());
//
//			Array productsArray = postgresConnection.createArrayOf("products",
//					store.getProducts().toArray((new ProductDto[store.getProducts().size()])));
//
//			callableStatement.setArray(2, productsArray);
//
//			ResultSet procedureReturn = callableStatement.executeQuery();
//
//			if (procedureReturn.next()) {
//				log.info(procedureReturn.getString(1));
//			}
//
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new StoreDaoException(ErrorMessages.SQL_INSERT_STORE_DAO_ERROR);
//		}
//	}

//	@Override
//	public void persist(String propertiesPath, Store storeToPersist) throws StoreDaoException {
//
//		log.info("Connecting to database");
//
//		try (PostgresConnector postgresConnector = new PostgresConnector();
//				Connection postgresConnection = postgresConnector.startDatabaseConnection(propertiesPath);
//				PreparedStatement preparedStatementStore = postgresConnection
//						.prepareStatement(Queries.CHECK_EXISTING_STORE.getQuery())) {
//
//			preparedStatementStore.setInt(1, storeToPersist.getId());
//
//			ResultSet existingStore = preparedStatementStore.executeQuery();
//
//			if (existingStore.next()) {
//
//				log.info("Store found at database");
//
//				storeToPersist.getProducts().forEach(product -> {
//					try (PreparedStatement preparedStatementProduct = postgresConnection
//							.prepareStatement(Queries.CHECK_EXISTING_PRODUCT_STORE.getQuery())) {
//
//						preparedStatementProduct.setInt(1, product.getId());
//						preparedStatementProduct.setInt(2, storeToPersist.getId());
//
//						ResultSet existingProductStore = preparedStatementProduct.executeQuery();
//
//						if (existingProductStore.next()) {
//
//							log.info("Product found at store");
//
//							try (PreparedStatement preparedStatementInventory = postgresConnection
//									.prepareStatement(Queries.UPDATE_INVENTORY.getQuery())) {
//
//								preparedStatementInventory.setInt(1,
//										existingProductStore.getInt("amount") + product.getInventory().getAmount());
//								preparedStatementInventory.setInt(2, existingProductStore.getInt("id"));
//
//								if (preparedStatementInventory.executeUpdate() == 1) {
//									log.info("Inventory updated");
//								}
//							}
//						} else {
//							try (PreparedStatement preparedStatementProductDatabase = postgresConnection
//									.prepareStatement(Queries.CHECK_EXISTING_PRODUCT_DATABASE.getQuery())) {
//
//								preparedStatementProductDatabase.setInt(1, product.getId());
//
//								ResultSet existingProductDatabase = preparedStatementProductDatabase.executeQuery();
//
//								if (existingProductDatabase.next()) {
//
//									log.info("Product found at database, persisting into this store");
//
//									try (PreparedStatement persistInventory = postgresConnection
//											.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {
//
//										persistInventory.setInt(1, product.getId());
//										persistInventory.setInt(2, product.getInventory().getStoreId());
//										persistInventory.setInt(3, product.getInventory().getAmount());
//										persistInventory.setBigDecimal(4, product.getInventory().getPrice());
//
//										if (persistInventory.executeUpdate() == 1) {
//											log.info("Inventory persisted");
//										}
//									}
//								} else {
//									try (PreparedStatement persistProduct = postgresConnection
//											.prepareStatement(Queries.PERSIST_PRODUCT.getQuery())) {
//
//										persistProduct.setString(1, product.getName());
//
//										if (persistProduct.executeUpdate() == 1) {
//
//											try (PreparedStatement persistInventory = postgresConnection
//													.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {
//
//												persistInventory.setInt(1, product.getId());
//												persistInventory.setInt(2, product.getInventory().getStoreId());
//												persistInventory.setInt(3, product.getInventory().getAmount());
//												persistInventory.setBigDecimal(4, product.getInventory().getPrice());
//
//												if (persistInventory.executeUpdate() == 1) {
//													log.info("Inventory persisted");
//												}
//											}
//										}
//									}
//								}
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//						}
//					} catch (SQLException e) {
//						log.error(e.getMessage(), e);
//					}
//				});
//			} else {
//				log.info("Starting new store persistence");
//
//				try (PreparedStatement preparedStatementInsertStore = postgresConnection
//						.prepareStatement(Queries.PERSIST_STORE.getQuery())) {
//
//					preparedStatementInsertStore.setString(1, storeToPersist.getName());
//					preparedStatementInsertStore.setString(2, storeToPersist.getDocument());
//
//					switch (preparedStatementInsertStore.executeUpdate()) {
//					case 1:
//						log.info("Store persisted successfully");
//
//						storeToPersist.getProducts().forEach(product -> {
//							try (PreparedStatement preparedStatementProductDatabase = postgresConnection
//									.prepareStatement(Queries.CHECK_EXISTING_PRODUCT_DATABASE.getQuery())) {
//
//								preparedStatementProductDatabase.setInt(1, product.getId());
//
//								ResultSet existingProductDatabase = preparedStatementProductDatabase.executeQuery();
//
//								if (existingProductDatabase.next()) {
//
//									log.info("Product found at database, persisting into new store");
//
//									try (PreparedStatement persistInventory = postgresConnection
//											.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {
//
//										persistInventory.setInt(1, product.getId());
//										persistInventory.setInt(2, product.getInventory().getStoreId());
//										persistInventory.setInt(3, product.getInventory().getAmount());
//										persistInventory.setBigDecimal(4, product.getInventory().getPrice());
//
//										if (persistInventory.executeUpdate() == 1) {
//											log.info("Inventory persisted");
//										}
//									}
//								} else {
//									try (PreparedStatement persistProduct = postgresConnection
//											.prepareStatement(Queries.PERSIST_PRODUCT.getQuery())) {
//
//										persistProduct.setString(1, product.getName());
//
//										if (persistProduct.executeUpdate() == 1) {
//
//											log.info("Product persisted at database, persisting into new store");
//
//											try (PreparedStatement persistInventory = postgresConnection
//													.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {
//
//												persistInventory.setInt(1, product.getId());
//												persistInventory.setInt(2, product.getInventory().getStoreId());
//												persistInventory.setInt(3, product.getInventory().getAmount());
//												persistInventory.setBigDecimal(4, product.getInventory().getPrice());
//
//												if (persistInventory.executeUpdate() == 1) {
//													log.info("Inventory persisted");
//												}
//											}
//										}
//									}
//								}
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//						});
//						break;
//					case 2:
//						log.info("Could not persist this store");
//						throw new Exception();
//					}
//				}
//			}
//
//		} catch (SQLException e) {
//			log.error(e.getMessage(), e);
//			throw new StoreDaoException(ErrorMessages.SQL_INSERT_STORE_DAO_ERROR);
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new StoreDaoException(ErrorMessages.INSERT_STORE_DAO_ERROR);
//		}
//	}
}
