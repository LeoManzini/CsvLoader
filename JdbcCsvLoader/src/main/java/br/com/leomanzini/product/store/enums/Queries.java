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
	
	FIND_FULL_STORE_BY_DOCUMENT(" SELECT i.id AS inventory_id, "
							  + " i.product_serie AS product_serial, "
							  + " i.product_id AS product_id, "
							  + " prod.nome AS product_name, "
							  + " i.amount AS amount, "
							  + " i.price AS price, "
							  + " i.store_document AS store_document, "
							  + " s.id AS store_id, "
							  + " s.nome AS store_name "
							  + " FROM inventory i "
							  + " LEFT JOIN stores s ON i.store_document = s.document "
							  + " LEFT JOIN products prod ON i.product_id = prod.id "
							  + " WHERE s.document = ? "),
	
	FIND_FULL_STORES(" SELECT i.id AS inventory_id, "
			  	   + " i.product_serie AS product_serial, "
			  	   + " i.product_id AS product_id, "
			  	   + " prod.nome AS product_name, "
			  	   + " i.amount AS amount, "
			  	   + " i.price AS price, "
			  	   + " i.store_document AS store_document, "
			  	   + " s.id AS store_id, "
			  	   + " s.nome AS store_name "
			  	   + " FROM inventory i "
			  	   + " LEFT JOIN stores s ON i.store_document = s.document "
			  	   + " LEFT JOIN products prod ON i.product_id = prod.id "),

	INSERT_PRODUCT(" INSERT INTO     "
	       		 + " products (nome) "
	       		 + " VALUES (?)      "),
	
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
					+ " FROM products "),
	
	FIND_PRODUCT_BY_NAME(" SELECT *       "
					   + " FROM products  "
					   + " WHERE nome = ? "),
	
	INSERT_INVENTORY(" INSERT INTO inventory          "
			       + " (product_serie, product_id,    "
			       + " store_document, amount, price) "
			       + " VALUES (?, ?, ?, ?, ?)         "),

	UPDATE_INVENTORY(" UPDATE inventory          "
			       + " SET amount = amount + ? , "
			       + " price = ?                 "
			       + " WHERE product_serie = ?   "
			       + " AND store_document = ?    "),
	
	DELETE_INVENTORY(" DELETE                  " 
			       + " FROM inventory          "
			       + " WHERE product_serie = ? "
			       + " AND store_document = ?  "),
	
	FIND_INVENTORY_BY_ID(" SELECT *               "
			           + " FROM inventory         "
			           + " WHERE product_id = ?   "
			           + " AND store_document = ? "),
	
	FIND_ALL_INVENTORY(" SELECT *       "
		             + " FROM inventory "),
	
	FIND_INVENTORY_BY_SERIE(" SELECT *                "
						  + " FROM inventory          "
						  + " WHERE product_serie = ? "
						  + " AND store_document = ?  ");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
