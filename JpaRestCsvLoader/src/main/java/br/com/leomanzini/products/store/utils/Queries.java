package br.com.leomanzini.products.store.utils;

public enum Queries {

	FIND_ALL_ABSTRACT("select t from T");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
}
