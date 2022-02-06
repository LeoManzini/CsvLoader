package br.com.leomanzini.products.store.services;

import java.io.InputStream;

import br.com.leomanzini.products.store.csv.CsvReader;
import br.com.leomanzini.products.store.dao.DaoFactory;
import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.exceptions.CsvReaderException;

@SuppressWarnings("unused")
public class FileUploadService {

	private static FileUploadService fileUploadServiceImplementation;

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
			StoreDto storeTopersist = reader.readCsv(inputStream);
			System.out.println(storeTopersist);
			return true;
		} catch (CsvReaderException e) {
			e.printStackTrace();
			return false;	
		}
	}
}
