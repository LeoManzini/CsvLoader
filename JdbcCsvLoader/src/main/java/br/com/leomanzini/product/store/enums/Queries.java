package br.com.leomanzini.product.store.enums;

public enum Queries {
	
	CHECK_EXISTING_STORE(" SELECT *     "
					   + " FROM store   "
					   + " WHERE id = ? "),
	
	CHECK_EXISTING_PRODUCT(" SELECT *         "
						 + " FROM product     "
						 + " WHERE id = ?     "
						 + " AND store_id = ? "),
	
	SELECT_INVENTORY(" SELECT i.amount      "
				   + " FROM inventory i     "
				   + " WHERE i.id = ?       "
				   + " AND i.product_id = ? "
				   + " AND i.store_id = ?   "),
	
	UPDATE_INVENTORY(" UPDATE inventory   "
				   + " SET amount = ?     "
				   + " WHERE id = ?       "
				   + " AND product_id = ? "
				   + " AND store_id = ?   "),
	
	PERSIST_STORE(" INSERT INTO store    "
				+ " (id, nome, document) "
				+ " VALUES (?, ?, ?)     "),
	
	PERSIST_PRODUCT(" INSERT INTO product         "
				  + " (id, nome, price, store_id) "
				  + " VALUES (?, ?, ?, ?)         "),
	
	PERSIST_INVENTORY(" INSERT INTO inventory              "
					+ " (id, product_id, store_id, amount) "
					+ " VALUES (?, ?, ?, ?)                ");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
