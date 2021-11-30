package br.com.leomanzini.product.store.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ProperLoader {

	private static final Logger Log = LogManager.getLogger(ProperLoader.class);

	/*Atribuímos os valores para pegar os valores no arquivo properties */
	private static final String PROPRIEDADES_URL = "database.url";
	private static final String PROPRIEDADES_USER = "database.username";
	private static final String PROPRIEDADES_PASSWORD = "database.password";

	/* onde setamos os valores (pegamos no properties) */
	private static String databaseUrl;
	private static String databaseUser;
	private static String databasePassword;

	private ProperLoader() {
	}
	
	public static void carregaPropriedadesBanco(String path) { 
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(path));
			
			databaseUrl = properties.getProperty(PROPRIEDADES_URL);
			databaseUser = properties.getProperty(PROPRIEDADES_USER);
			databasePassword = properties.getProperty(PROPRIEDADES_PASSWORD);
	
		}catch (Exception e) {
			 Log.error(e.getMessage());
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
