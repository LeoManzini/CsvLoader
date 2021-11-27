package br.com.leomanzini.product.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.ParametersException;
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
			Executor persistenceExecutor = new PersistenceExecutor();
			persistenceExecutor.execute(propertiesPath, csvPath);
		} catch (Exception e) {

		}
	}
}
