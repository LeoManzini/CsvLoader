package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.services.StoreService;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/store")
public class StoreResource {
	
	StoreService service = new StoreService();

	@GET
	@Path("{document}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStoreProducts(@PathParam("document") Integer document) {
		return service.getStoreProducts(document);
	}
}
