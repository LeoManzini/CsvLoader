package br.com.leomanzini.product.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.dtos.StoreDto;
import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.enums.Queries;
import br.com.leomanzini.product.store.exceptions.StoreDaoException;

public class InsertStoreDao implements StoreDao {

	private static final Logger log = LogManager.getLogger(InsertStoreDao.class);

	@Override
	public void persist(String propertiesPath, StoreDto storeToPersist) throws StoreDaoException {

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
							.prepareStatement(Queries.CHECK_EXISTING_PRODUCT.getQuery())) {

						preparedStatementProduct.setInt(1, product.getId());
						preparedStatementProduct.setInt(2, product.getStoreId());

						ResultSet existingProduct = preparedStatementProduct.executeQuery();

						if (existingProduct.next()) {

							log.info("Product found at database");

							try (PreparedStatement preparedStatementFoundInventory = postgresConnection
									.prepareStatement(Queries.SELECT_INVENTORY.getQuery());
									PreparedStatement preparedStatementInventory = postgresConnection
											.prepareStatement(Queries.UPDATE_INVENTORY.getQuery())) {

								preparedStatementFoundInventory.setInt(1, product.getId());
								preparedStatementFoundInventory.setInt(2, storeToPersist.getId());

								ResultSet existingInventory = preparedStatementFoundInventory.executeQuery();

								if (existingInventory.next()) {
									preparedStatementInventory.setInt(1,
											existingInventory.getInt("amount") + product.getInventory().getAmount());
									preparedStatementInventory.setInt(2, product.getId());
									preparedStatementInventory.setInt(3, storeToPersist.getId());

									if (preparedStatementInventory.executeUpdate() == 1) {
										log.info("Inventory updated");
									}
								}
							}
						} else {
							try (PreparedStatement preparedStatementInsertProducts = postgresConnection
									.prepareStatement(Queries.PERSIST_PRODUCT.getQuery())) {

								preparedStatementInsertProducts.setInt(1, product.getId());
								preparedStatementInsertProducts.setString(2, product.getName());
								preparedStatementInsertProducts.setBigDecimal(3, product.getPrice());
								preparedStatementInsertProducts.setInt(4, product.getStoreId());

								if (preparedStatementInsertProducts.executeUpdate() == 1) {

									log.info("New product registered: " + product.getName() + ", for store: "
											+ storeToPersist.getName());

									try (PreparedStatement preparedStatementInventory = postgresConnection
											.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {

										preparedStatementInventory.setInt(1, product.getInventory().getId());
										preparedStatementInventory.setInt(2, product.getId());
										preparedStatementInventory.setInt(3, storeToPersist.getId());
										preparedStatementInventory.setInt(4, product.getInventory().getAmount());

										if (preparedStatementInventory.executeUpdate() == 1) {
											log.info("Inventory created for new product");
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

					preparedStatementInsertStore.setInt(1, storeToPersist.getId());
					preparedStatementInsertStore.setString(2, storeToPersist.getName());
					preparedStatementInsertStore.setString(3, storeToPersist.getDocument());

					switch (preparedStatementInsertStore.executeUpdate()) {
						case 1:
							log.info("Store persisted successfully");
	
							try (PreparedStatement preparedStatementInsertProducts = postgresConnection
									.prepareStatement(Queries.PERSIST_PRODUCT.getQuery())) {
	
								storeToPersist.getProducts().forEach(product -> {
									try {
										preparedStatementInsertProducts.setInt(1, product.getId());
										preparedStatementInsertProducts.setString(2, product.getName());
										preparedStatementInsertProducts.setBigDecimal(3, product.getPrice());
										preparedStatementInsertProducts.setInt(4, product.getStoreId());
	
										if (preparedStatementInsertProducts.executeUpdate() == 1) {
	
											log.info("New product registered: " + product.getName() + ", for store: "
													+ storeToPersist.getName());
	
											try (PreparedStatement preparedStatementInventory = postgresConnection
													.prepareStatement(Queries.PERSIST_INVENTORY.getQuery())) {
	
												preparedStatementInventory.setInt(1, product.getId());
												preparedStatementInventory.setInt(2, storeToPersist.getId());
												preparedStatementInventory.setInt(3, product.getInventory().getAmount());
	
												if (preparedStatementInventory.executeUpdate() == 1) {
													log.info("Inventory created for new product");
												}
											}
										}
									} catch (SQLException e) {
										e.printStackTrace();
									}
								});
							}
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
