package br.com.leomanzini.products.store.resources;

import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.services.ProductService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/product")
public class ProductResource {
	
	private final ProductService service = new ProductService();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertProductAtDatabase(ProductDto productToInsert) {
		return service.insertNewProductToDatabase(productToInsert);
	}
}
