package br.com.leomanzini.product.store.dtos;

import java.math.BigDecimal;

public class ProductDto {

	private Integer id;
	private String name;
	private BigDecimal price;
	private Integer storeId;
	private InventoryDto inventory;

	public ProductDto() {
	}

	public ProductDto(Integer id, String name, BigDecimal price, Integer storeId, InventoryDto inventory) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.storeId = storeId;
		this.inventory = inventory;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public InventoryDto getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDto inventory) {
		this.inventory = inventory;
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
		ProductDto other = (ProductDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product id: " + id + ", name: " + name + ", price: " + price + ", storeId: " + storeId + ", inventory: "
				+ inventory.getAmount();
	}
}
