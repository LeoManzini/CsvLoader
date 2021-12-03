package br.com.leomanzini.product.store.enums;

public enum ErrorMessages {
	
	PARAMETER_ERROR("Invalid arguments number, exit -1"),
	PROPERTIES_LOADER_ERROR("Something went wrong while reading properties file, exit -2"),
	CSV_READER_ERROR("Something went wrong while reading csv file, exit -3"),
	INSERT_STORE_DAO_ERROR("Could not persist the store entity, exit -4"),
	SQL_INSERT_STORE_DAO_ERROR("Something went wrong with your SQL, exit -5"),
	PERSISTENCE_EXECUTOR_ERROR("Something went wrong while executing the database operation, exit -6");
	
	private String message;
	
	private ErrorMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
