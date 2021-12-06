package br.com.leomanzini.product.store.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.PropertiesLoaderException;

public abstract class PropertiesLoader {
	
	private static final Logger log = LogManager.getLogger(PropertiesLoader.class);
	
	private static final String PROPERTIES_DB_URL = "database.url";
	private static final String PROPERTIES_DB_USER = "database.user";
	private static final String PROPERTIES_DB_PASSWORD = "database.password";
	
	private static final String PROPERTIES_INSERT_QUERY = "query.insert";
	private static final String PROPERTIES_SELECT_QUERY = "query.select";
	private static final String PROPERTIES_UPDATE_QUERY = "query.update";
	private static final String PROPERTIES_DELETE_QUERY = "query.delete";
	
	private static String databaseUrl;
	private static String databaseUser;
	private static String databasePassword;
	
	private static String insertQuery;
	private static String selectQuery;
	private static String updateQuery;
	private static String deleteQuery;
	
	private PropertiesLoader() {
	}
	
	public static void load(String path) throws PropertiesLoaderException {
		
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(path));
			
			databaseUrl = properties.getProperty(PROPERTIES_DB_URL);
			databaseUser = properties.getProperty(PROPERTIES_DB_USER);
			databasePassword = properties.getProperty(PROPERTIES_DB_PASSWORD);
			
			insertQuery = (!properties.getProperty(PROPERTIES_INSERT_QUERY).equals("null")) ? properties.getProperty(PROPERTIES_INSERT_QUERY) : "void";
			selectQuery = (!properties.getProperty(PROPERTIES_SELECT_QUERY).equals("null")) ? properties.getProperty(PROPERTIES_SELECT_QUERY) : "void";
			updateQuery = (!properties.getProperty(PROPERTIES_UPDATE_QUERY).equals("null")) ? properties.getProperty(PROPERTIES_UPDATE_QUERY) : "void";
			deleteQuery = (!properties.getProperty(PROPERTIES_DELETE_QUERY).equals("null")) ? properties.getProperty(PROPERTIES_DELETE_QUERY) : "void";
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PropertiesLoaderException(ErrorMessages.PROPERTIES_LOADER_ERROR);
		}
	}
	
	public static String getDatabaseUrl() {
		return databaseUrl;
	}
	
	public static String getDatabaseUser() {
		return databaseUser;
	}
	
	public static String getDatabasePassword() {
		return databasePassword;
	}

	public static String getInsertQuery() {
		return insertQuery;
	}

	public static String getSelectQuery() {
		return selectQuery;
	}

	public static String getUpdateQuery() {
		return updateQuery;
	}

	public static String getDeleteQuery() {
		return deleteQuery;
	}
}
