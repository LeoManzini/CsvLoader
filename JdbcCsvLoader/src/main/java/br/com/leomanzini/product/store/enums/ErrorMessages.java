package br.com.leomanzini.product.store.enums;

public enum ErrorMessages {
	
	PARAMETER_ERROR("Invalid arguments number, exit -1");
	
	private String message;
	
	private ErrorMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
