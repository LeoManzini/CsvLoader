package br.com.leomanzini.products.store.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.leomanzini.products.store.dao.DaoFactory;
import br.com.leomanzini.products.store.dao.impl.ProductDaoImpl;
import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.dto.ResponseObjectDto;
import br.com.leomanzini.products.store.entities.Product;
import br.com.leomanzini.products.store.utils.SystemMessages;
import jakarta.ws.rs.core.Response;

public class ProductService {
	
	private static ProductService productServiceImplementation;
	
	private final ProductDaoImpl productDao = DaoFactory.getProductDaoImplementation();
	
	public static ProductService getStoreServiceImplementation() {
		if (productServiceImplementation == null) {
			productServiceImplementation = new ProductService();
		}
		return productServiceImplementation;
	}
	
	public Response insertNewProductToDatabase(ProductDto productToInsert) {
		Product productAtDatabase = productDao.findBySerial(productToInsert.getProductSerial());
		if (productAtDatabase == null) {
			if (productDao.insert(new Product(productToInsert))) {
				return returnMessage(Response.Status.OK, SystemMessages.PRODUCT_INSERTED);
			} else {
				return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.PRODUCT_NOT_INSERTED);
			}
		} else {
			return returnMessage(Response.Status.BAD_REQUEST, SystemMessages.PRODUCT_ALREADY_AT_DATABASE);
		}
	}
	
	private Response returnMessage(Response.Status status, SystemMessages message) {
		return Response.status(status).entity(ResponseObjectDto.builder().message(message.getMessage())
				.time(DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())).build()).build();
	}
}
