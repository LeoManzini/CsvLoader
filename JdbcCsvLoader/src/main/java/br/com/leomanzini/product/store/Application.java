package br.com.leomanzini.product.store;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.connector.PostgresConnector;

public class Application {

	private static final Logger log = LogManager.getLogger(Application.class); 
	
	public static void main(String[] args) {
		
		log.info("Hello World!!");
		
		log.info("Leo, agora vai");
		
		try {
			PostgresConnector conector = new PostgresConnector();
			Connection con = conector.startDataBaseConnection(args[0]);
			log.info("Leo deu certo ");
			
		}catch(Exception e) { 
			log.info("Leo deu errado!! ");
		}
	}
}
