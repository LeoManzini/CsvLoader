package br.com.leomanzini.products.store.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class PropertiesLoader {

	private static final Logger LOG = LogManager.getLogger(PropertiesLoader.class);
	
	private static final String DATABASE_DRIVER = "javax.persistence.jdbc.driver";
	private static final String DATABASE_URL = "javax.persistence.jdbc.url";
	private static final String DATABASE_USERNAME = "javax.persistence.jdbc.username";
	private static final String DATABASE_PASSWORD = "javax.persistence.jdbc.password";
	
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_CREATION = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	
	private static final String PROPERTIES_PATH = "/application.properties";
	
	private static String databaseDriver;
	private static String databaseUrl;
	private static String databaseUsername;
	private static String databasePassword;
	
	private static String hibernateDialect;
	private static String hibernateCreation;
	private static String hibernateShowSql;
	private static String hibernateFormatSql;
	
	public static void load() {
		Properties props = new Properties();

		try {
			InputStream propertiesFile = PropertiesLoader.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
			
			if (propertiesFile != null) {
                props.load(propertiesFile);
			}
			
			databaseDriver = props.getProperty(DATABASE_DRIVER);
			databaseUrl = props.getProperty(DATABASE_URL);
			databaseUsername = props.getProperty(DATABASE_USERNAME);
			databasePassword = props.getProperty(DATABASE_PASSWORD);
			
			hibernateDialect = props.getProperty(HIBERNATE_DIALECT);
			hibernateCreation = props.getProperty(HIBERNATE_CREATION);
			hibernateShowSql = props.getProperty(HIBERNATE_SHOW_SQL);
			hibernateFormatSql = props.getProperty(HIBERNATE_FORMAT_SQL);
			
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	public static EntityManagerFactory createEntityManagerFactoryConfigured() {
		Properties props = new Properties();
		
		try {
			InputStream propertiesFile = PropertiesLoader.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
			
			if (propertiesFile != null) {
                props.load(propertiesFile);
			}
			
			return Persistence.createEntityManagerFactory("development", props);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			
			// Substituir por throw new PropertiesLoaderException(Exception.CREATE_ENTITY_MANAGER);
			return null;
		}
	}

	public static String getDatabaseDriver() {
		return databaseDriver;
	}

	public static String getDatabaseUrl() {
		return databaseUrl;
	}

	public static String getDatabaseUsername() {
		return databaseUsername;
	}

	public static String getDatabasePassword() {
		return databasePassword;
	}

	public static String getHibernateDialect() {
		return hibernateDialect;
	}

	public static String getHibernateCreation() {
		return hibernateCreation;
	}

	public static String getHibernateShowSql() {
		return hibernateShowSql;
	}

	public static String getHibernateFormatSql() {
		return hibernateFormatSql;
	}
}
