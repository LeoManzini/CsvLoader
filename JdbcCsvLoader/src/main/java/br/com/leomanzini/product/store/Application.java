package br.com.leomanzini.product.store;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

	private static final Logger log = LogManager.getLogger(Application.class); 
	
	public static void main(String[] args) throws SQLException {
		
		log.info("Hello World!!");
	}
}
