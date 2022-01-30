package br.com.leomanzini.products.store.utils;

public enum SystemMessages {
	
	STORE_NOT_FOUND("Store not found"),
	PRODUCT_STORE_NOT_FOUND("Product not found for this store");
	
	private String message;
	
	private SystemMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
