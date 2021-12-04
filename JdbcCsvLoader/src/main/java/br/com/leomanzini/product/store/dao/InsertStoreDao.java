package br.com.leomanzini.product.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.dtos.StoreDto;
import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.StoreDaoException;

public class InsertStoreDao implements StoreDao {

	private static final Logger log = LogManager.getLogger(InsertStoreDao.class);

	private static final String insertQuery = "";

	@Override
	public void persist(String propertiesPath, StoreDto storeToPersist) throws StoreDaoException {

		try (PostgresConnector postgresConnector = new PostgresConnector();
				Connection postgresConnection = postgresConnector.startDatabaseConnection(propertiesPath);
				PreparedStatement preparedStatement = postgresConnection.prepareStatement(insertQuery)) {
			
			log.info("Connecting to database");
			
			switch (preparedStatement.executeUpdate()) {
				case 1:
					log.info("Insertion completed successfully");
					break;
				case 2:
					log.error("Insertion failed");
					throw new Exception();
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
