package br.com.leomanzini.products.store.services;

import java.io.InputStream;

import br.com.leomanzini.products.store.dao.DaoFactory;
import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;

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
		return false;	
	}
}
