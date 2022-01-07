package br.com.leomanzini.product.store.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.csv.StoreCsvReader;
import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.CsvExecutorException;
import br.com.leomanzini.product.store.exceptions.CsvReaderException;
import br.com.leomanzini.product.store.model.entities.Store;

public class CsvExecutor implements Executor {
	
	private static final Logger log = LogManager.getLogger(CsvExecutor.class);

	private Store storeItens = null;

	@Override
	public void execute(String csvPath) throws CsvExecutorException {
		try {
			StoreCsvReader csvReader = new StoreCsvReader();
			
			log.info("Starting csv execution");
			
			storeItens = csvReader.readCsv(csvPath);
			
			log.info("Csv execution successfully");
			
		} catch (CsvReaderException e) {
			log.error(e.getMessage(), e);
			throw new CsvExecutorException(ErrorMessages.CSV_READER_ERROR);
		}
	}

	public Store getCsvItens() {
		return storeItens;
	}
}
