package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.services.StoreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldResource {
	
	StoreService service = new StoreService();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld() {
		return "Hello World!" + service.getStoreProducts(1);
	}
}
