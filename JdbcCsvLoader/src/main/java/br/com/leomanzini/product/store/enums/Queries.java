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
	
	FIND_STORE_BY_ID(" SELECT document AS store_document, "
				   + " id AS store_id, "
				   + " nome AS store_name "
			 	   + " FROM stores   "
			 	   + " WHERE id = ?  "),
	
	FIND_ALL_STORE(" SELECT document AS store_document, "
			     + " id AS store_id, "
			     + " nome AS store_name "
		 	     + " FROM stores   "),
	
	FIND_STORE_BY_DOCUMENT(" SELECT *            "
	   					 + " FROM stores         "
	   					 + " WHERE document = ?  "),
	
	FIND_FULL_STORE_BY_DOCUMENT(" SELECT i.id AS inventory_id, "
							  + " i.product_serial AS product_serial, "
							  + " prod.nome AS product_name, "
							  + " i.amount AS amount, "
							  + " i.price AS price, "
							  + " i.store_document AS store_document, "
							  + " s.id AS store_id, "
							  + " s.nome AS store_name "
							  + " FROM inventory i "
							  + " LEFT JOIN stores s ON i.store_document = s.document "
							  + " LEFT JOIN products prod ON i.product_serial = prod.serial "
							  + " WHERE s.document = ? "),
	
	FIND_FULL_STORES(" SELECT i.id AS inventory_id, "
			  	   + " i.product_serial AS product_serial, "
			  	   + " prod.nome AS product_name, "
			  	   + " i.amount AS amount, "
			  	   + " i.price AS price, "
			  	   + " i.store_document AS store_document, "
			  	   + " s.id AS store_id, "
			  	   + " s.nome AS store_name "
			  	   + " FROM inventory i "
			  	   + " LEFT JOIN stores s ON i.store_document = s.document "
			  	   + " LEFT JOIN products prod ON i.product_serial = prod.serial "
			  	   + " ORDER BY inventory_id "),

	INSERT_PRODUCT(" INSERT INTO     "
	       		 + " products (nome, serial) "
	       		 + " VALUES (?, ?)      "),
	
	UPDATE_PRODUCT(" UPDATE products "
		     	 + " SET nome = ?    "
		     	 + " WHERE id = ?    "),
	
	DELETE_PRODUCT(" DELETE        "
				 + " FROM products "
		     	 + " WHERE id = ?  "),
	
	FIND_PRODUCT_BY_ID(" SELECT *      "
			   		 + " FROM products "
			         + " WHERE id = ?  "),
	
	FIND_ALL_PRODUCTS(" SELECT *      "
					+ " FROM products "
					+ " ORDER BY id   "),
	
	FIND_PRODUCT_BY_SERIAL(" SELECT *       "
					   + " FROM products  "
					   + " WHERE serial = ? "),
	
	INSERT_INVENTORY(" INSERT INTO inventory            "
			       + " (amount, price,                  "
			       + " serial, store_document)  "
			       + " VALUES (?, ?, ?, ?)           "),

	UPDATE_INVENTORY(" UPDATE inventory          "
			       + " SET amount = amount + ? , "
			       + " price = ?                 "
			       + " WHERE serial = ?   "
			       + " AND store_document = ?    "),
	
	DELETE_INVENTORY(" DELETE                  " 
			       + " FROM inventory          "
			       + " WHERE serial = ? "
			       + " AND store_document = ?  "),
	
	FIND_ALL_INVENTORY(" SELECT *       "
		             + " FROM inventory "
		             + " ORDER BY id    "),
	
	FIND_INVENTORY_BY_SERIE(" SELECT *                "
						  + " FROM inventory          "
						  + " WHERE serial = ? "
						  + " AND store_document = ?  ");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
