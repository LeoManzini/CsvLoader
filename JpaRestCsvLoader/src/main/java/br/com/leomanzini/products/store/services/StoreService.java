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
			return Response.status(Response.Status.NOT_FOUND)
					.entity(ResponseObjectDto.builder().message(SystemMessages.STORE_NOT_FOUND.getMessage())
							.time(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())).build())
					.build();
		} else {
			StoreDto storeResponse = null;
			for (Inventory inventory : storeInventory) {
				storeResponse = (storeResponse == null) ? new StoreDto(inventory.getStore()) : storeResponse;
				ProductDto product = new ProductDto(inventory);
				storeResponse.getProducts().add(product);
			}
			return Response.ok(storeResponse, MediaType.APPLICATION_JSON).build();
		}
	}

	public Response getStoreProduct(Integer storeDocument, Integer productSerial) {
		Inventory productInventory = inventoryDao.findStoreProduct(storeDocument, productSerial);

		if (productInventory == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(ResponseObjectDto.builder().message(SystemMessages.PRODUCT_STORE_NOT_FOUND.getMessage())
							.time(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())).build())
					.build();
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
				return Response.ok(ResponseObjectDto.builder().message(SystemMessages.STORE_INSERTED.getMessage())
						.time(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())).build()).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(ResponseObjectDto.builder().message(SystemMessages.STORE_NOT_PERSISTED.getMessage())
						.time(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())).build())
				.build();
	}

	public Response insertProductIntoStore(ProductDto productToInsert) {
		return null;
	}

	public Response insertNewProductToDatabase(ProductDto productToInsert) {
		return null;
	}

	public Response updateStore(StoreDto storeToUpdate) {
		return null;
	}

	public Response updateProduct(ProductDto productToUpdate) {
		return null;
	}

	public Response deleteStore(Integer id) {
		return null;
	}

	public Response deleteProduct(Long serial) {
		return null;
	}
}
