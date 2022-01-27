package br.com.leomanzini.products.store.utils;

public enum Queries {

	STORE_FIND_ALL("select st from Store st"),
	STORE_FIND_BY_ID("select st from Store st where st.id = :storeId"),
	STORE_INSERT("insert into stores (nome, document) values (:name, :document)"),
	STORE_UPDATE("update stores set nome = :name, document = :document where id = :id"),
	STORE_DELETE("delete from Store s where s.id = :id"),
	
	PRODUCT_FIND_ALL("select pd from Product pd"),
	PRODUCT_FIND_BY_ID("select pd from Product pd where pd.id = :productId"),
	PRODUCT_INSERT("insert into products (nome, serial) values (:name, :serial)"),
	PRODUCT_UPDATE("update products set nome = :name where id = :id"),
	PRODUCT_DELETE("delete from Product pd where pd.id = :id");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
