package br.com.leomanzini.product.store.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class ProperLoader {

	private static final Logger Log = LogManager.getLogger(ProperLoader.class);
	
	private static final String PROPRIEDADES_URL = "database.url";
	private static final String PROPRIEDADES_USER = "database.user";
	private static final String PROPRIEDADES_PASSWORD = "database.password";
	
	private static String databaseUrl;
	private static String databaseUser;
	private static String databasePassword;
	

	public void carregaPropriedadesBanco(String path) { 
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream(path));
			
			databaseUrl = properties.getProperty(PROPRIEDADES_URL);
			databaseUser = properties.getProperty(PROPRIEDADES_USER);
			databasePassword = properties.getProperty(PROPRIEDADES_PASSWORD);
			
			
		}catch (Exception e) {
			 Log.error(e.getMessage());
		}
		
		/*try{

		     String url = “jdbc:mysql://localhost/tutorial1”;

		     String usuario = “postgres”;

		     String senha = “1234”;

		     Connection conexao = DriverManager.getConnection(url, usuario, senha);

		}catch(SQLException sqle){

		     sqle.printStackTrace();

		} */
		
		
	}
	private ProperLoader () {
		
	}
	
	
}
