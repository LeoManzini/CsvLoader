package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.services.StoreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldResource {
	
	StoreService service = new StoreService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		return service.getStoreProducts(10);
	}
}
