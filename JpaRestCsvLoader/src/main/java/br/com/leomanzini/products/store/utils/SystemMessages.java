package br.com.leomanzini.products.store.utils;

public enum SystemMessages {
	
	STORE_NOT_FOUND("Store not found"),
	PRODUCT_STORE_NOT_FOUND("Product not found for this store"), 
	STORE_INSERTED("Store inserted successfully"),
	STORE_NOT_PERSISTED("There already have a store with this document at database"),
	STORE_NOT_DELETED("Store not found at database"),
	STORE_DELETED("Store deleted successfully");
	
	private String message;
	
	private SystemMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
