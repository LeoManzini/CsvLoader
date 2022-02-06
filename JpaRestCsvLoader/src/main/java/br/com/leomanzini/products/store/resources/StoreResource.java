package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.exceptions.CsvReaderException;
import br.com.leomanzini.products.store.services.StoreService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/store")
public class StoreResource {

	private final StoreService service = StoreService.getStoreServiceImplementation();

	@GET
	@Path("{document}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStoreProducts(@PathParam("document") Integer document) throws CsvReaderException {
		return service.getStoreProducts(document);
	}

	@GET
	@Path("{document}/{serial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStoreProduct(@PathParam("document") Integer document, @PathParam("serial") Integer productSerial) {
		return service.getStoreProduct(document, productSerial);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertNewStore(StoreDto storeToInsert) {
		return service.insertStore(storeToInsert);
	}
	
	@POST
	@Path("{document}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertNewProductStore(ProductDto productToInsert, @PathParam("document") Integer document) {
		return service.insertProductIntoStore(document, productToInsert);
	}
	
	@DELETE
	@Path("{document}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStore(@PathParam("document") Integer document) {
		return service.deleteStore(document);
	}
	
	@DELETE
	@Path("{document}/{serial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("document") Integer document, @PathParam("serial") Integer productSerial) {
		return service.deleteProduct(document, productSerial);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStore(StoreDto storeToUpdate) {
		return service.updateStore(storeToUpdate);
	}
	
	@PUT
	@Path("{document}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("document") Integer document, ProductDto productToUpdate) {
		return service.updateProduct(document, productToUpdate);
	}
}
