package br.com.leomanzini.product.store.model.entities;

import java.util.List;

public class Store {

	private Integer id;
	private String name;
	private Integer document;
	private List<Product> products;

	public Store() {
	}

	public Store(Integer id, String name, Integer document, List<Product> products) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDocument() {
		return document;
	}

	public void setDocument(Integer data) {
		this.document = data;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Store id: " + id + ", name: " + name + ", document: " + document + ", products: " + products;
	}
}
