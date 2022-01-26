package br.com.leomanzini.products.store.utils;

public enum Queries {

	STORE_FIND_ALL("select st from Store st");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
