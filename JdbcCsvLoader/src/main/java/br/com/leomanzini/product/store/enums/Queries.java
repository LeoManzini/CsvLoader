package br.com.leomanzini.product.store.enums;

public enum Queries {
	
	CHECK_EXISTING_STORE(" SELECT *     "
					   + " FROM store   "
					   + " WHERE id = ? "),
	
	CHECK_EXISTING_PRODUCT_STORE(" SELECT *             "
						          + " FROM inventory       "
						          + " WHERE product_id = ? "
						          + " AND store_id = ?     "),
	
	UPDATE_INVENTORY(" UPDATE inventory "
				   + " SET amount = ?   "
				   + " WHERE id = ?     "),
	
	CHECK_EXISTING_PRODUCT_DATABASE(" SELECT *     "
								  + " FROM product "
								  + " WHERE id = ? "),
	
	PERSIST_STORE(" INSERT INTO store "
				+ " (nome, document)  "
				+ " VALUES (?, ?)     "),
	
	PERSIST_PRODUCT(" INSERT INTO product "
				  + " (nome)              "
				  + " VALUES (?)          "),
	
	PERSIST_INVENTORY(" INSERT INTO inventory                 "
					+ " (product_id, store_id, amount, price) "
					+ " VALUES (?, ?, ?, ?)                   ");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
