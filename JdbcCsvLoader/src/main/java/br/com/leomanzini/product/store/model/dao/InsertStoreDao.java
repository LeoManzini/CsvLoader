package br.com.leomanzini.product.store.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.enums.Queries;
import br.com.leomanzini.product.store.exceptions.StoreDaoException;
import br.com.leomanzini.product.store.model.entities.Store;

public class InsertStoreDao implements StoreDao {

	private static final Logger log = LogManager.getLogger(InsertStoreDao.class);

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

	@Override
	public void persist(String propertiesPath, Store storeToPersist) throws StoreDaoException {

		log.info("Connecting to database");

		try (PostgresConnector postgresConnector = new PostgresConnector();
				Connection postgresConnection = postgresConnector.startDatabaseConnection(propertiesPath);
				PreparedStatement preparedStatementStore = postgresConnection
						.prepareStatement(Queries.CHECK_EXISTING_STORE.getQuery())) {

			preparedStatementStore.setInt(1, storeToPersist.getId());

			ResultSet existingStore = preparedStatementStore.executeQuery();

			if (existingStore.next()) {

				log.info("Store found at database");

				storeToPersist.getProducts().forEach(product -> {
					try (PreparedStatement preparedStatementProduct = postgresConnection
							.prepareStatement(Queries.CHECK_EXISTING_PRODUCT_STORE.getQuery())) {

						preparedStatementProduct.setInt(1, product.getId());
						preparedStatementProduct.setInt(2, storeToPersist.getId());

						ResultSet existingProductStore = preparedStatementProduct.executeQuery();

						if (existingProductStore.next()) {

							log.info("Product found at store");

							try (PreparedStatement preparedStatementInventory = postgresConnection
									.prepareStatement(Queries.UPDATE_INVENTORY.getQuery())) {

								preparedStatementInventory.setInt(1,
										existingProductStore.getInt("amount") + product.getInventory().getAmount());
								preparedStatementInventory.setInt(2, existingProductStore.getInt("id"));

								if (preparedStatementInventory.executeUpdate() == 1) {
									log.info("Inventory updated");
								}
							}
						} else {
							try (PreparedStatement preparedStatementProductDatabase = postgresConnection
									.prepareStatement(Queries.CHECK_EXISTING_PRODUCT_DATABASE.getQuery())) {

								preparedStatementProductDatabase.setInt(1, product.getId());

								ResultSet existingProductDatabase = preparedStatementProductDatabase.executeQuery();

								if (existingProductDatabase.next()) {

									log.info("Product found at database, persisting into this store");

									try (PreparedStatement persistInventory = postgresConnection
											.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {

										persistInventory.setInt(1, product.getId());
										persistInventory.setInt(2, product.getInventory().getStoreId());
										persistInventory.setInt(3, product.getInventory().getAmount());
										persistInventory.setBigDecimal(4, product.getInventory().getPrice());

										if (persistInventory.executeUpdate() == 1) {
											log.info("Inventory persisted");
										}
									}
								} else {
									try (PreparedStatement persistProduct = postgresConnection
											.prepareStatement(Queries.PERSIST_PRODUCT.getQuery())) {

										persistProduct.setString(1, product.getName());

										if (persistProduct.executeUpdate() == 1) {

											try (PreparedStatement persistInventory = postgresConnection
													.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {

												persistInventory.setInt(1, product.getId());
												persistInventory.setInt(2, product.getInventory().getStoreId());
												persistInventory.setInt(3, product.getInventory().getAmount());
												persistInventory.setBigDecimal(4, product.getInventory().getPrice());

												if (persistInventory.executeUpdate() == 1) {
													log.info("Inventory persisted");
												}
											}
										}
									}
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} catch (SQLException e) {
						log.error(e.getMessage(), e);
					}
				});
			} else {
				log.info("Starting new store persistence");

				try (PreparedStatement preparedStatementInsertStore = postgresConnection
						.prepareStatement(Queries.PERSIST_STORE.getQuery())) {

					preparedStatementInsertStore.setString(1, storeToPersist.getName());
					preparedStatementInsertStore.setString(2, storeToPersist.getDocument());

					switch (preparedStatementInsertStore.executeUpdate()) {
					case 1:
						log.info("Store persisted successfully");

						storeToPersist.getProducts().forEach(product -> {
							try (PreparedStatement preparedStatementProductDatabase = postgresConnection
									.prepareStatement(Queries.CHECK_EXISTING_PRODUCT_DATABASE.getQuery())) {

								preparedStatementProductDatabase.setInt(1, product.getId());

								ResultSet existingProductDatabase = preparedStatementProductDatabase.executeQuery();

								if (existingProductDatabase.next()) {

									log.info("Product found at database, persisting into new store");

									try (PreparedStatement persistInventory = postgresConnection
											.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {

										persistInventory.setInt(1, product.getId());
										persistInventory.setInt(2, product.getInventory().getStoreId());
										persistInventory.setInt(3, product.getInventory().getAmount());
										persistInventory.setBigDecimal(4, product.getInventory().getPrice());

										if (persistInventory.executeUpdate() == 1) {
											log.info("Inventory persisted");
										}
									}
								} else {
									try (PreparedStatement persistProduct = postgresConnection
											.prepareStatement(Queries.PERSIST_PRODUCT.getQuery())) {

										persistProduct.setString(1, product.getName());

										if (persistProduct.executeUpdate() == 1) {

											log.info("Product persisted at database, persisting into new store");

											try (PreparedStatement persistInventory = postgresConnection
													.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {

												persistInventory.setInt(1, product.getId());
												persistInventory.setInt(2, product.getInventory().getStoreId());
												persistInventory.setInt(3, product.getInventory().getAmount());
												persistInventory.setBigDecimal(4, product.getInventory().getPrice());

												if (persistInventory.executeUpdate() == 1) {
													log.info("Inventory persisted");
												}
											}
										}
									}
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						});
						break;
					case 2:
						log.info("Could not persist this store");
						throw new Exception();
					}
				}
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.SQL_INSERT_STORE_DAO_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new StoreDaoException(ErrorMessages.INSERT_STORE_DAO_ERROR);
		}
	}

}
