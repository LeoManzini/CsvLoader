package br.com.leomanzini.products.store.utils;

public enum SystemMessages {
	
	STORE_NOT_FOUND("Store not found at database"),
	PRODUCT_STORE_NOT_FOUND("Product not found for this store"), 
	STORE_INSERTED("Store inserted successfully"),
	STORE_NOT_PERSISTED("There already have a store with this document at database"),
	STORE_DELETED("Store deleted successfully"),
	STORE_UPDATED("Store updated successfully");
	
	private String message;
	
	private SystemMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
