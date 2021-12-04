package br.com.leomanzini.product.store.executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.dao.InsertStoreDao;
import br.com.leomanzini.product.store.dao.StoreDao;
import br.com.leomanzini.product.store.dtos.StoreDto;
import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.PersistenceExecutorException;
import br.com.leomanzini.product.store.exceptions.StoreDaoException;

public class PersistenceExecutor implements Executor {
	
	private static final Logger log = LogManager.getLogger(PersistenceExecutor.class);
	
	private StoreDto storeItens;

	public PersistenceExecutor(StoreDto storeItens) {
		this.storeItens = storeItens;
	}
	
	@Override
	public void execute(String propertiesPath) throws PersistenceExecutorException {
		try {
			StoreDao insertStore = new InsertStoreDao();
			
			log.info("Starting persistence execution");
			
			insertStore.persist(propertiesPath, storeItens);
			
			log.info("Persistence execution successfully");
			
		} catch (StoreDaoException e) {
			log.error(e.getMessage(), e);
			throw new PersistenceExecutorException(ErrorMessages.PERSISTENCE_EXECUTOR_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PersistenceExecutorException(ErrorMessages.PERSISTENCE_EXECUTOR_ERROR);
		}
	}
}