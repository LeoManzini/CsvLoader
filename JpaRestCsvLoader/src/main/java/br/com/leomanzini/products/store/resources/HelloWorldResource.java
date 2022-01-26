package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.dao.GenericDaoInterface;
import br.com.leomanzini.products.store.dao.impl.StoreDaoImpl;
import br.com.leomanzini.products.store.model.entities.Store;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld() {
		GenericDaoInterface<Store> storeDao = new StoreDaoImpl();
		return "Hello World! " + storeDao.insert(Store.builder().name("Adidas").document(7845).build());
	}
}
