package br.com.leomanzini.product.store.enums;

public enum ErrorMessages {
	
	PARAMETER_ERROR("Invalid arguments number, exit -1"),
	PROPERTIES_LOADER_ERROR("Something went wrong while reading properties file, exit -2"),
	CSV_READER_ERROR("Something went wrong while reading csv file, exit -3");
	
	private String message;
	
	private ErrorMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
