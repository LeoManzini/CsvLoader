package br.com.leomanzini.product.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.ParametersException;

public class Application {

	private static final Logger log = LogManager.getLogger(Application.class); 
	
	public static void main(String[] args) throws Exception {
		
		if(args.length != 2) {
			log.error("Invalid arguments number!");
			
			log.info("Expected: ");
			log.info("java -jar CsvLoader <propertiesPath> <csvPath>");
			log.info("<propertiesPath> where the properties of your project are;");
			log.info("<csvPath> where the project will find your csv file to load.");
			
			throw new ParametersException(ErrorMessages.PARAMETER_ERROR);
		}
	}
}
