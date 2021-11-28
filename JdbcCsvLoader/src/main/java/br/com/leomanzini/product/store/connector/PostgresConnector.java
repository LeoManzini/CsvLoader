package br.com.leomanzini.product.store.connector;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.exceptions.PropertiesLoaderException;
import br.com.leomanzini.product.store.utils.PropertiesLoader;

public class PostgresConnector implements DatabaseConnector, Closeable {
	
	private static final Logger log = LogManager.getLogger(PostgresConnector.class); 
	
	private Connection databaseConnection;

	@Override
	public Connection startDatabaseConnection(String propertiesPath) {
		if(databaseConnection == null) {
			try {
				PropertiesLoader.load(propertiesPath);
				
				databaseConnection = DriverManager.getConnection(
						PropertiesLoader.getDatabaseUrl(), 
						PropertiesLoader.getDatabaseUser(),
						PropertiesLoader.getDatabasePassword());
			} 
			catch (PropertiesLoaderException e) {
				log.error(e.getMessage(), e);
				System.exit(-1);
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
				System.exit(-1);
			}
		}
		
		return databaseConnection;
	}

	@Override
	public void close() {
		try {
			databaseConnection.close();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
		}
	}
}
