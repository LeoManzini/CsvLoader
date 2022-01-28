package br.com.leomanzini.products.store.services;

import org.jvnet.hk2.annotations.Service;

import br.com.leomanzini.products.store.dao.impl.InventoryDaoImpl;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.dto.ResponseObjectDto;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.model.entities.Store;
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

	public StoreDto getStoreProducts(Integer storeId) {
		Store storeResponse = storeDao.findById(storeId);
		return StoreDto.builder().storeId(storeResponse.getId()).storeName(storeResponse.getName())
				.storeDocument(storeResponse.getDocument()).build();
	}

	public ProductDto getStoreProduct(Integer id) {
		return null;
	}

	public Response insertStore(StoreDto storeToInsert) {
		return Response.ok(ResponseObjectDto.builder().build().toString(), MediaType.APPLICATION_JSON).build();
	}

	public Response insertProductIntoStore(ProductDto productToInsert) {
		return Response.ok(ResponseObjectDto.builder().build().toString(), MediaType.APPLICATION_JSON).build();
	}

	public Response insertNewProductToDatabase(ProductDto productToInsert) {
		return Response.ok(ResponseObjectDto.builder().build().toString(), MediaType.APPLICATION_JSON).build();
	}

	public Response updateStore(StoreDto storeToUpdate) {
		return Response.ok(ResponseObjectDto.builder().build().toString(), MediaType.APPLICATION_JSON).build();
	}

	public Response updateProduct(ProductDto productToUpdate) {
		return Response.ok(ResponseObjectDto.builder().build().toString(), MediaType.APPLICATION_JSON).build();
	}

	public Response deleteStore(Integer id) {
		return Response.ok(ResponseObjectDto.builder().build().toString(), MediaType.APPLICATION_JSON).build();
	}

	public Response deleteProduct(Long serial) {
		return Response.ok(ResponseObjectDto.builder().build().toString(), MediaType.APPLICATION_JSON).build();
	}
}
