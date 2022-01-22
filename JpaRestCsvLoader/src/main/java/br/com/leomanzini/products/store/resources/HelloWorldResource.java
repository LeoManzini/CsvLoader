package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.database.connector.DatabaseConnector;
import br.com.leomanzini.products.store.database.connector.PostgresConnector;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld() {
		DatabaseConnector connector = new PostgresConnector();
		connector.createEntityManager();
		return "Hello World! ";
	}
}
