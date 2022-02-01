package br.com.leomanzini.products.store.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.jvnet.hk2.annotations.Service;

import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.dto.ResponseObjectDto;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.model.entities.Inventory;
import br.com.leomanzini.products.store.model.entities.Store;
import br.com.leomanzini.products.store.utils.SystemMessages;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Service
@SuppressWarnings("unused")
public class StoreService {

	private final StoreDaoImpl storeDao;
	private final ProductDaoImpl productDao;
	private final InventoryDaoImpl inventoryDao;

	public StoreService() {
		storeDao = new StoreDaoImpl();
		productDao = new ProductDaoImpl();
		inventoryDao = new InventoryDaoImpl();
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
		Store storeExists = storeDao.findByDocument(storeToInsert.getStoreDocument());
		if (storeExists == null) {
			if (storeDao.insert(new Store(storeToInsert))) {
				return returnMessage(Response.Status.OK, SystemMessages.STORE_INSERTED);
			}
		}
		return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.STORE_NOT_PERSISTED);
	}

	public Response insertProductIntoStore(ProductDto productToInsert) {
		return null;
	}

	public Response insertNewProductToDatabase(ProductDto productToInsert) {
		return null;
	}

	public Response updateStore(StoreDto storeToUpdate) {
		Store storeUpdatable = storeDao.findByDocument(storeToUpdate.getStoreDocument());
		if (storeUpdatable == null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.STORE_NOT_FOUND);
		}
		storeUpdatable.setName(storeToUpdate.getStoreName());
		storeDao.update(storeUpdatable);
		return returnMessage(Response.Status.OK, SystemMessages.STORE_UPDATED);
	}

	public Response updateProduct(ProductDto productToUpdate) {
		return null;
	}

	public Response deleteStore(Integer document) {
		Store storeExisting = storeDao.findByDocument(document);
		if (storeExisting == null) {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.STORE_NOT_FOUND);
		}
		storeDao.deleteByDocument(document);
		return returnMessage(Response.Status.OK, SystemMessages.STORE_DELETED);
	}

	public Response deleteProduct(Long serial) {
		return null;
	}

	private Response returnMessage(Response.Status status, SystemMessages message) {
		return Response.status(status).entity(ResponseObjectDto.builder().message(message.getMessage())
				.time(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())).build()).build();
	}
}
