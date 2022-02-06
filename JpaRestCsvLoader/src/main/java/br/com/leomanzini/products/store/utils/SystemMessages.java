package br.com.leomanzini.products.store.utils;

public enum SystemMessages {
	
	STORE_NOT_FOUND("Store not found at database"),
	PRODUCT_STORE_NOT_FOUND("Product not found for this store"), 
	STORE_INSERTED("Store inserted successfully"),
	STORE_NOT_PERSISTED("There already have a store with this document at database"),
	STORE_DELETED("Store deleted successfully"),
	STORE_UPDATED("Store updated successfully"),
	PRODUCT_STORE_FOUND("Product already found at this store, to update use put method"), 
	PRODUCT_STORE_INSERTED("Product inserted at this store"), 
	PRODUCT_STORE_NOT_INSERTED("Found errors while inserting product at store"), 
	PRODUCT_NOT_FOUND("Product not found at database, please verify the database first"), 
	PRODUCT_ALREADY_AT_DATABASE("Product already at database"), 
	PRODUCT_INSERTED("Product inserted at database successfully"),
	PRODUCT_NOT_INSERTED("Product not inserted at database"), 
	PRODUCT_DELETED("Product deleted from store"),
	PRODUCT_UPDATED("Product updated"), 
	CSV_READER_ERROR("Error while reading csv"), 
	FILE_UPLOAD_SUCCESS("File uploaded successfully"), 
	FILE_UPLOAD_ERROR("Error trying to upload file");
	
	private String message;
	
	private SystemMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
