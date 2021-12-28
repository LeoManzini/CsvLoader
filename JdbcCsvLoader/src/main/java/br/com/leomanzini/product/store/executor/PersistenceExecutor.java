package br.com.leomanzini.product.store.executor;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.PersistenceExecutorException;
import br.com.leomanzini.product.store.model.dao.DaoFactory;
import br.com.leomanzini.product.store.model.dao.StoreDao;
import br.com.leomanzini.product.store.model.entities.Store;

public class PersistenceExecutor implements Executor {
	
	private static final Logger log = LogManager.getLogger(PersistenceExecutor.class);
	
	private Store storeItens;

	public PersistenceExecutor(Store storeItens) {
		this.storeItens = storeItens;
	}
	
	// TODO ajeitar a classe storeDao para trazer a lista preenchida de produtos da loja
	// TODO testar todos os métodos das classes DAO

	@Override
	public void execute(String propertiesPath) throws Exception {
		DaoFactory jdbcFactory = new DaoFactory(propertiesPath);
//		
//		InventoryDao inventory = jdbcFactory.createInventoryDao();
//		ProductDao product = jdbcFactory.createProductDao();
		StoreDao store = jdbcFactory.createStoreDao();
		
		try {
			Store foundStore = store.findStoreWithProducts(1);
			
			log.info(foundStore);
			
			List<Store> foundStores = store.findAllStoresWithProducts();
			
			foundStores.forEach(stores -> log.info(stores));
//			if (store.findAtDatabase(storeItens.getDocument())) {
//				log.info("Store found at database, checking the products list for " + storeItens.getName());
//				storeItens.getProducts().forEach(producto -> {
//					try {
//						if (inventory.findAtDatabase(producto.getInventory().getProductSerie(), storeItens.getDocument())) {
//							log.info("Product " + producto.getName() + " found at this store, increasing inventory");
//							inventory.update(producto.getInventory());
//							log.info("Inventory increased");
//						} else {
//							log.info("Product not found at this store, registering new product " + producto.getName());
//							if (product.findAtDatabase(producto)) {
//								inventory.insert(producto.getInventory());
//							} else {
//								product.insert(producto);
//								inventory.insert(producto.getInventory());
//							}
//							log.info("Product registered successfully");
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				});
//			} else {
//				log.info("Registering new store " + storeItens.getName());
//				store.insert(storeItens);
//				storeItens.getProducts().forEach(producto -> {
//					try {
//						log.info("Registering new product " + producto.getName() + " for the store inventory");
//						if (product.findAtDatabase(producto)) {
//							inventory.insert(producto.getInventory());
//						} else {
//							product.insert(producto);
//							inventory.insert(producto.getInventory());
//						}
//						log.info("Product registered successfully");
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				});
//			}
//		} catch (StoreDaoException e) {
//			log.error(e.getMessage(), e);
//			throw new PersistenceExecutorException(ErrorMessages.PERSISTENCE_EXECUTOR_STORE_ERROR);
//		} catch (ProductDaoException e) {
//			log.error(e.getMessage(), e);
//			throw new PersistenceExecutorException(ErrorMessages.PERSISTENCE_EXECUTOR_PRODUCT_ERROR);
//		} catch (InventoryDaoException e) {
//			log.error(e.getMessage(), e);
//			throw new PersistenceExecutorException(ErrorMessages.PERSISTENCE_EXECUTOR_INVENTORY_ERROR);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new PersistenceExecutorException(ErrorMessages.PERSISTENCE_EXECUTOR_PRODUCT_ERROR);
		}
	}
	
	/**
	 * Old method version, calling just the class with the persistence codes
	 */
//	@Override
//	public void execute(String propertiesPath) throws PersistenceExecutorException {
//		try {
//			StoreDao insertStore = new StoreDaoImplJdbc();
//			
//			log.info("Starting persistence execution");
//			
//			insertStore.persist(propertiesPath, storeItens);
//			
//			log.info("Persistence execution successfully");
//			
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new PersistenceExecutorException(ErrorMessages.PERSISTENCE_EXECUTOR_ERROR);
//		}
//	}
}
