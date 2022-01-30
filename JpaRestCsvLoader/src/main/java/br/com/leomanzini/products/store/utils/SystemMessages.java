package br.com.leomanzini.products.store.utils;

public enum SystemMessages {
	
	STORE_NOT_FOUND("Store not found");
	
	private String message;
	
	private SystemMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
