package br.com.leomanzini.products.store.model.entities;

import java.math.BigDecimal;

public class Inventory {

	private Integer id;
	private Integer productSerie;
	private Integer productId;
	private Integer storeDocument;
	private Integer amount;
	private BigDecimal price;

	public Inventory() {
	}

	public Inventory(Integer productSerie, Integer productId, Integer storeId, Integer amount, BigDecimal price) {
		this.productSerie = productSerie;
		this.productId = productId;
		this.storeDocument = storeId;
		this.amount = amount;
		this.price = price;
	}
	
	public Inventory(Integer productSerie, Integer storeId, Integer amount, BigDecimal price) {
		this.productSerie = productSerie;
		this.storeDocument = storeId;
		this.amount = amount;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductSerie() {
		return productSerie;
	}

	public void setProductSerie(Integer productSerie) {
		this.productSerie = productSerie;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getStoreDocument() {
		return storeDocument;
	}

	public void setStoreDocument(Integer storeDocument) {
		this.storeDocument = storeDocument;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
		Inventory other = (Inventory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inventory id: " + id + ", productSerie: " + productSerie + ", productId: " + productId
				+ ", storeDocument: " + storeDocument + ", amount: " + amount + ", price: " + price;
	}
}
