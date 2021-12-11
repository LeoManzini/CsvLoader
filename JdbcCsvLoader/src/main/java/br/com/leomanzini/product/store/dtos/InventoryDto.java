package br.com.leomanzini.product.store.dtos;

import java.math.BigDecimal;

public class InventoryDto {

	private Integer id;
	private Integer productId;
	private Integer storeId;
	private Integer amount;
	private BigDecimal price;

	public InventoryDto() {
	}

	public InventoryDto(Integer productId, Integer storeId, Integer amount, BigDecimal price) {
		this.productId = productId;
		this.storeId = storeId;
		this.amount = amount;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
		InventoryDto other = (InventoryDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "product id: " + productId + ", store id: " + storeId + ", product amount: " + amount + ", product price: " + price;
	}
}
