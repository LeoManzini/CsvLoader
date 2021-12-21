package br.com.leomanzini.product.store.enums;

public enum Queries {
	
	INSERT_STORE(" INSERT INTO stores "
			   + " (nome, document)   "
			   + " VALUES (?, ?)      "),
	
	UPDATE_STORE(" UPDATE stores       "
	     	   + " SET nome = ?        "
	     	   + " WHERE document = ?  "),

	DELETE_STORE(" DELETE        "
			   + " FROM stores   "
	     	   + " WHERE id = ?  "),
	
	FIND_STORE_BY_ID(" SELECT *      "
			 	   + " FROM stores   "
			 	   + " WHERE id = ?  "),
	
	FIND_ALL_STORE(" SELECT *    "
				 + " FROM stores "),
	
	FIND_STORE_BY_DOCUMENT(" SELECT *            "
	   					 + " FROM stores         "
	   					 + " WHERE document = ?  "),

	INSERT_PRODUCT(" INSERT INTO products "
	       		 + " (nome)               "
	       		 + " VALUES (?)           "),
	
	UPDATE_PRODUCT(" UPDATE products "
		     	 + " SET nome = ?    "
		     	 + " WHERE id = ?    "),
	
	DELETE_PRODUCT(" DELETE        "
				 + " FROM products "
		     	 + " WHERE id = ?  "),
	
	FIND_PRODUCT(" SELECT *      "
			   + " FROM products "
			   + " WHERE id = ?  "),
	
	FIND_ALL_PRODUCTS(" SELECT *      "
					+ " FROM products "),
	
	INSERT_INVENTORY(" INSERT INTO inventory                       "
			       + " (product_id, store_document, amount, price) "
			       + " VALUES (?, ?, ?, ?)                         "),

	UPDATE_INVENTORY(" UPDATE inventory          "
			       + " SET amount = amount + ? , "
			       + " price = ?                 "
			       + " WHERE product_id = ?      "
			       + " AND store_document = ?    "),
	
	DELETE_INVENTORY(" DELETE                 "
			       + " FROM inventory         "
			       + " WHERE product_id = ?   "
			       + " AND store_document = ? "),
	
	FIND_INVENTORY(" SELECT *               "
		         + " FROM inventory         "
		         + " WHERE product_id = ?   "
		         + " AND store_document = ? "),
	
	FIND_ALL_INVENTORY(" SELECT *       "
		             + " FROM inventory ");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
