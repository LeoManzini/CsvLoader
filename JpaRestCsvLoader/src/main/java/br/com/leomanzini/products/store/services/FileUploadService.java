package br.com.leomanzini.products.store.services;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.products.store.csv.CsvReader;
import br.com.leomanzini.products.store.dao.DaoFactory;
import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.entities.Inventory;
import br.com.leomanzini.products.store.exceptions.CsvReaderException;

@SuppressWarnings("unused")
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

			if (storeDao.findById(storeToPersist.getStoreDocument()) != null) {
				log.info("Store found at database, checking the products list for " + storeToPersist.getStoreName());
				storeToPersist.getProducts().forEach(product -> {
					try {
						if (inventoryDao.findStoreProduct(storeToPersist.getStoreDocument(), product.getProductSerial()) != null) {
							log.info("Product " + product.getProductName() + " found at this store, increasing inventory");
							inventoryDao.update(Inventory.builder().build());
							log.info("Inventory increased");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}

			return true;
		} catch (CsvReaderException e) {
			e.printStackTrace();
			return false;
		}
	}
}
