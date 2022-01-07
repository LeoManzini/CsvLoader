package br.com.leomanzini.product.store.connector;

import java.sql.Connection;

public interface DatabaseConnector {
	
	public Connection startDatabaseConnection(String propertiesPath);
}
