package br.com.leomanzini.product.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.CsvReaderException;
import br.com.leomanzini.product.store.exceptions.ParametersException;
import br.com.leomanzini.product.store.exceptions.PersistenceExecutorException;
import br.com.leomanzini.product.store.executor.CsvExecutor;
import br.com.leomanzini.product.store.executor.Executor;
import br.com.leomanzini.product.store.executor.PersistenceExecutor;

public class Application {
	
	private static final Logger log = LogManager.getLogger(Application.class);
	
	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			log.error("Invalid arguments number!");

			log.info("Expected: ");
			log.info("java -jar CsvLoader <propertiesPath> <csvPath>");
			log.info("<propertiesPath> where the properties of your project are;");
			log.info("<csvPath> where the project will find your csv file to load.");

			throw new ParametersException(ErrorMessages.PARAMETER_ERROR);
		}

		String propertiesPath = args[0];
		String csvPath = args[1];

		try {
			log.info("Properties file: " + propertiesPath);
			log.info("Csv file: " + csvPath);
			
			log.info("Starting application");
			
			Executor csvReaderExecutor = new CsvExecutor();
			csvReaderExecutor.execute(csvPath);
			Executor persistenceExecutor = new PersistenceExecutor(((CsvExecutor) csvReaderExecutor).getCsvItens());
			persistenceExecutor.execute(propertiesPath);
			
			log.info("Application run successfully");
			
		} catch (CsvReaderException e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
		} catch (PersistenceExecutorException e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
		}
	}
}
