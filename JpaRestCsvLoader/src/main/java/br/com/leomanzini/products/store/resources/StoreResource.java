package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.services.StoreService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/store/{document}")
public class StoreResource {

	StoreService service = new StoreService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStoreProducts(@PathParam("document") Integer document) {
		return service.getStoreProducts(document);
	}

	@GET
	@Path("{serial}")
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
}
