package br.com.leomanzini.product.store.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class PropertiesLoader {
	
	private static final Logger log = LogManager.getLogger(PropertiesLoader.class);
	
	private static final String PROPERTIES_DB_URL = "database.url";
	private static final String PROPERTIES_DB_USER = "database.user";
	private static final String PROPERTIES_DB_PASSWORD = "database.password";
	
	private static String databaseUrl;
	private static String databaseUser;
	private static String databasePassword;
	
	private PropertiesLoader() {
	}
	
	public static void load(String path) {
		
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(path));
			
			databaseUrl = properties.getProperty(PROPERTIES_DB_URL);
			databaseUser = properties.getProperty(PROPERTIES_DB_USER);
			databasePassword = properties.getProperty(PROPERTIES_DB_PASSWORD);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
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
}
