package br.com.leomanzini.product.store.dtos;

import java.util.List;

public class StoreDto {

	private Integer id;
	private String name;
	private String document;
	private List<ProductDto> products;

	public StoreDto() {
	}

	public StoreDto(Integer id, String name, String document, List<ProductDto> products) {
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

	public String getDocument() {
		return document;
	}

	public void setDocument(String data) {
		this.document = data;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
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
		StoreDto other = (StoreDto) obj;
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
