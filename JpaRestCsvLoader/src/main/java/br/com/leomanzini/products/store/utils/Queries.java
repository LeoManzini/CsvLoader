package br.com.leomanzini.products.store.utils;

public enum Queries {

	STORE_FIND_ALL("select st from Store st"),
	
	STORE_FIND_BY_ID("select st from Store st where st.id = :storeId"),
	
	STORE_INSERT("insert into stores (nome, document) values (:name, :document)");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
