package br.com.leomanzini.products.store.services;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.products.store.csv.CsvReader;
import br.com.leomanzini.products.store.dao.DaoFactory;
import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.entities.Inventory;
import br.com.leomanzini.products.store.entities.Product;
import br.com.leomanzini.products.store.entities.Store;
import br.com.leomanzini.products.store.exceptions.CsvReaderException;

public class FileUploadService {

	private static FileUploadService fileUploadServiceImplementation;
	private static final Logger log = LogManager.getLogger(FileUploadService.class);

	private final StoreDaoImpl storeDao = DaoFactory.getStoreDaoImplementation();
	private final ProductDaoImpl productDao = DaoFactory.getProductDaoImplementation();
	private final InventoryDaoImpl inventoryDao = DaoFactory.getInventoryDaoImplementation();

	public static FileUploadService getFileUploadServiceImplementation() {
		if (fileUploadServiceImplementation == null) {
			fileUploadServiceImplementation = new FileUploadService();
		}
		return fileUploadServiceImplementation;
	}

	public boolean executeDatabaseRoutine(InputStream inputStream) {
		try {
			CsvReader reader = new CsvReader();
			StoreDto storeToPersist = reader.readCsv(inputStream);

			if (storeDao.findStore(storeToPersist.getStoreDocument())) {
				log.info("Store found at database, checking the products list for " + storeToPersist.getStoreName());
				storeToPersist.getProducts().forEach(product -> {
					try {
						if (inventoryDao.findStoreProduct(storeToPersist.getStoreDocument(),
								product.getProductSerial()) != null) {
							log.info("Product " + product.getProductName()
									+ " found at this store, increasing inventory");
							inventoryDao.updateProduct(instanciateInventory(product, storeToPersist));
							log.info("Inventory increased");
						} else {
							log.info("Product not found at this store, registering new product "
									+ product.getProductName());
							if (productDao.findById(product.getProductSerial()) != null) {
								inventoryDao.insert(instanciateInventory(product, storeToPersist));
							} else {
								productDao.insert(instanciateProduct(product));
								inventoryDao.insert(instanciateInventory(product, storeToPersist));
							}
							log.info("Product registered successfully");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				return true;
			} else {
				log.info("Registering new store " + storeToPersist.getStoreName());
				storeDao.insert(instanciateStore(storeToPersist));
				storeToPersist.getProducts().forEach(product -> {
					try {
						log.info("Registering new product " + product.getProductName() + " for the store inventory");
						if (productDao.findById(product.getProductSerial()) != null) {
							inventoryDao.insert(instanciateInventory(product, storeToPersist));
						} else {
							productDao.insert(instanciateProduct(product));
							inventoryDao.insert(instanciateInventory(product, storeToPersist));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				return true;
			}
		} catch (CsvReaderException e) {
			e.printStackTrace();
			return false;
		}
	}

	private Store instanciateStore(StoreDto storeToInsert) {
		return Store.builder().document(storeToInsert.getStoreDocument()).name(storeToInsert.getStoreName()).build();
	}

	private Inventory instanciateInventory(ProductDto product, StoreDto storeToPersist) {
		return Inventory.builder().amount(product.getAmount()).price(product.getPrice())
				.product(Product.builder().serial(product.getProductSerial()).build())
				.store(Store.builder().document(storeToPersist.getStoreDocument()).build()).build();
	}

	private Product instanciateProduct(ProductDto product) {
		return Product.builder().name(product.getProductName()).serial(product.getProductSerial()).build();
	}
}
