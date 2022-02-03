package br.com.leomanzini.products.store.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.jvnet.hk2.annotations.Service;

import br.com.leomanzini.products.store.dao.DaoFactory;
import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.dto.ResponseObjectDto;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.entities.Inventory;
import br.com.leomanzini.products.store.entities.Product;
import br.com.leomanzini.products.store.entities.Store;
import br.com.leomanzini.products.store.utils.SystemMessages;
import jakarta.ws.rs.core.Response;

@Service
public class StoreService {
	
	private static StoreService storeServiceImplementation;

	private final StoreDaoImpl storeDao = DaoFactory.getStoreDaoImplementation();
	private final ProductDaoImpl productDao = DaoFactory.getProductDaoImplementation();
	private final InventoryDaoImpl inventoryDao = DaoFactory.getInventoryDaoImplementation();

	public static StoreService getStoreServiceImplementation() {
		if (storeServiceImplementation == null) {
			storeServiceImplementation = new StoreService();
		}
		return storeServiceImplementation;
	}

	public Response getStoreProducts(Integer storeDocument) {
		List<Inventory> storeInventory = inventoryDao.findByDocument(storeDocument);

		if (storeInventory.isEmpty()) {
			return returnMessage(Response.Status.NOT_FOUND, SystemMessages.STORE_NOT_FOUND);
		} else {
			StoreDto storeResponse = null;
			for (Inventory inventory : storeInventory) {
				storeResponse = (storeResponse == null) ? new StoreDto(inventory.getStore()) : storeResponse;
				ProductDto product = new ProductDto(inventory);
				storeResponse.getProducts().add(product);
			}
			return Response.ok(storeResponse).build();
		}
	}

	public Response getStoreProduct(Integer storeDocument, Integer productSerial) {
		Store store = storeDao.findById(storeDocument);
		if (store == null) {
			return returnMessage(Response.Status.NOT_FOUND, SystemMessages.STORE_NOT_FOUND);
		}
		Inventory productInventory = inventoryDao.findStoreProduct(storeDocument, productSerial);
		if (productInventory == null) {
			return returnMessage(Response.Status.NOT_FOUND, SystemMessages.PRODUCT_STORE_NOT_FOUND);
		} else {
			StoreDto storeResponse = new StoreDto(productInventory.getStore());
			ProductDto product = new ProductDto(productInventory);
			storeResponse.getProducts().add(product);
			return Response.ok(storeResponse).build();
		}
	}

	public Response insertStore(StoreDto storeToInsert) {
		Store storeExists = storeDao.findById(storeToInsert.getStoreDocument());
		if (storeExists == null) {
			if (storeDao.insert(new Store(storeToInsert))) {
				return returnMessage(Response.Status.OK, SystemMessages.STORE_INSERTED);
			}
		}
		return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.STORE_NOT_PERSISTED);
	}

	public Response insertProductIntoStore(Integer storeDocument, ProductDto productToInsert) {
		Store storeUpdatable = storeDao.findById(storeDocument);
		if (storeUpdatable == null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.STORE_NOT_FOUND);
		}
		Inventory inventoryUpdatable = inventoryDao.findStoreProduct(storeDocument, productToInsert.getProductSerial());
		if (inventoryUpdatable != null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.PRODUCT_STORE_FOUND);
		} else {
			Product productAtDatabase = productDao.findById(productToInsert.getProductSerial());
			if (productAtDatabase != null) {				
				Inventory inventoryToInsert = instanciateInventory(storeUpdatable, productToInsert);
				if (inventoryDao.insert(inventoryToInsert)) {
					return returnMessage(Response.Status.OK, SystemMessages.PRODUCT_STORE_INSERTED);
				}
			} else {
				return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.PRODUCT_NOT_FOUND);
			}
		}
		return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.PRODUCT_STORE_NOT_INSERTED);
	}

	public Response updateStore(StoreDto storeToUpdate) {
		Store storeUpdatable = storeDao.findById(storeToUpdate.getStoreDocument());
		if (storeUpdatable == null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.STORE_NOT_FOUND);
		}
		storeUpdatable.setName(storeToUpdate.getStoreName());
		storeDao.update(storeUpdatable);
		return returnMessage(Response.Status.OK, SystemMessages.STORE_UPDATED);
	}

	public Response updateProduct(Integer storeDocument, ProductDto productToUpdate) {
		Inventory productInventory = inventoryDao.findStoreProduct(storeDocument, productToUpdate.getProductSerial());
		if (productInventory == null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.PRODUCT_STORE_NOT_FOUND);
		}
		productInventory.setAmount(productToUpdate.getAmount());
		productInventory.setPrice(productToUpdate.getPrice());
		inventoryDao.update(productInventory);
		return returnMessage(Response.Status.OK, SystemMessages.PRODUCT_UPDATED);
	}

	public Response deleteStore(Integer document) {
		Store storeExisting = storeDao.findById(document);
		if (storeExisting == null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.STORE_NOT_FOUND);
		}
		storeDao.deleteByDocument(document);
		return returnMessage(Response.Status.OK, SystemMessages.STORE_DELETED);
	}

	public Response deleteProduct(Integer storeDocument, Integer productSerial) {
		Inventory productInventory = inventoryDao.findStoreProduct(storeDocument, productSerial);
		if (productInventory == null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.PRODUCT_STORE_NOT_FOUND);
		}
		inventoryDao.delete(productInventory.getId());
		return returnMessage(Response.Status.OK, SystemMessages.PRODUCT_DELETED);
	}

	private Response returnMessage(Response.Status status, SystemMessages message) {
		return Response.status(status).entity(ResponseObjectDto.builder().message(message.getMessage())
				.time(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())).build()).build();
	}

	private Inventory instanciateInventory(Store storeUpdatable, ProductDto productToInsert) {
		return Inventory.builder().product(Product.builder().name(productToInsert.getProductName())
						.serial(productToInsert.getProductSerial()).build())
				.store(storeUpdatable).amount(productToInsert.getAmount()).price(productToInsert.getPrice()).build();
	}
}
