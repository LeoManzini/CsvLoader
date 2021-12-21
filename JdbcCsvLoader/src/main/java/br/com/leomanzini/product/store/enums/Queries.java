package br.com.leomanzini.product.store.enums;

public enum Queries {
	
	INSERT_STORE(" INSERT INTO store "
			   + " (nome, document)  "
			   + " VALUES (?, ?)     "),
	
	UPDATE_STORE(" UPDATE store       "
	     	   + " SET nome = ?       "
	     	   + " WHERE document = ? "),

	DELETE_STORE(" DELETE       "
			   + " FROM store   "
	     	   + " WHERE id = ? "),
	
	FIND_STORE_BY_ID(" SELECT *     "
			 	   + " FROM store   "
			 	   + " WHERE id = ? "),
	
	FIND_ALL_STORE(" SELECT *   "
				 + " FROM store "),
	
	FIND_STORE_BY_DOCUMENT(" SELECT *           "
	   					 + " FROM store         "
	   					 + " WHERE document = ? "),

	INSERT_PRODUCT(" INSERT INTO product "
	       		 + " (nome)              "
	       		 + " VALUES (?)          "),
	
	UPDATE_PRODUCT(" UPDATE product "
		     	 + " SET nome = ?   "
		     	 + " WHERE id = ?   "),
	
	DELETE_PRODUCT(" DELETE       "
				 + " FROM product "
		     	 + " WHERE id = ? "),
	
	FIND_PRODUCT(" SELECT *     "
			   + " FROM product "
			   + " WHERE id = ? "),
	
	FIND_ALL_PRODUCTS(" SELECT *     "
					+ " FROM product "),
	
	INSERT_INVENTORY(" INSERT INTO inventory                 "
			       + " (product_id, store_id, amount, price) "
			       + " VALUES (?, ?, ?, ?)                   "),

	UPDATE_INVENTORY(" UPDATE inventory          "
			       + " SET amount = amount + ? , "
			       + " price = ?                 "
			       + " WHERE product_id = ?      "
			       + " AND store_id = ?          "),
	
	DELETE_INVENTORY(" DELETE               "
			       + " FROM inventory       "
			       + " WHERE product_id = ? "
			       + " AND store_id = ?     "),
	
	FIND_INVENTORY(" SELECT *             "
		         + " FROM inventory       "
		         + " WHERE product_id = ? "
		         + " AND store_id = ?     "),
	
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
