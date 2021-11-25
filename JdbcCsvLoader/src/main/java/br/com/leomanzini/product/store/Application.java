package br.com.leomanzini.product.store;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.connector.PostgresConnector;

public class Application {

	private static final Logger log = LogManager.getLogger(Application.class); 
	
	public static void main(String[] args) throws SQLException {
		
		try(PostgresConnector postgres = new PostgresConnector(); 
				Connection connection = postgres.startDatabaseConnection(args[0])) {
			log.info("Conectou na base suave");
		} catch (Exception e) {
			log.error("Não conectou na base");
			System.exit(-2);
		}
		
		log.info("Final feliz");
	}
}
