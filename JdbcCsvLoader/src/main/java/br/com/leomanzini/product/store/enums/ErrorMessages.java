package br.com.leomanzini.product.store.enums;

public enum ErrorMessages {
	
	PARAMETER_ERROR("Invalid arguments number, exit -1"),
	
	PROPERTIES_LOADER_ERROR("Something went wrong while reading properties file, exit -2"),
	
	CSV_READER_ERROR("Something went wrong while reading csv file, exit -3"),
	CSV_EXECUTOR_ERROR("Something wrong with your csv, exit -4"),
	
	STORE_INSERT_ERROR("Error while trying to insert data to store, exit -4"),
	STORE_UPDATE_ERROR("Error while updating store, exit -5"),
	STORE_DELETE_ERROR("Error while deleting store, exit -6"),
	STORE_FIND_ERROR("Error while searching store, exit -7"),
	STORE_FIND_ALL_ERROR("Error while searching for stores, exit -8"),
	
	PRODUCT_INSERT_ERROR("Error while trying to insert data to product, exit -9"),
	PRODUCT_UPDATE_ERROR("Error while updating product, exit -10"),
	PRODUCT_DELETE_ERROR("Error while deleting product, exit -11"),
	PRODUCT_FIND_ERROR("Error while searching product, exit -12"),
	PRODUCT_FIND_ALL_ERROR("Error while searching for products, exit -13"),
	
	INVENTORY_INSERT_ERROR("Error while trying to insert data to inventory, exit -14"),
	INVENTORY_UPDATE_ERROR("Error while updating inventory, exit -15"),
	INVENTORY_DELETE_ERROR("Error while deleting inventory, exit -16"),
	INVENTORY_FIND_ERROR("Error while searching inventory, exit -17"),
	INVENTORY_FIND_ALL_ERROR("Error while searching for inventories, exit -18"),
	
	PERSISTENCE_EXECUTOR_STORE_ERROR("Something went wrong while executing the stores operation, exit -19"),
	PERSISTENCE_EXECUTOR_PRODUCT_ERROR("Something went wrong while executing the product operation, exit -20"),
	PERSISTENCE_EXECUTOR_INVENTORY_ERROR("Something went wrong while executing the inventory operation, exit -21");
	
	private String message;
	
	private ErrorMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
