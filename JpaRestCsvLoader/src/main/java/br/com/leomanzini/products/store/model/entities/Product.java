package br.com.leomanzini.products.store.model.entities;

public class Product {

	private Integer id;
	private String name;
	private Inventory inventory;

	public Product() {
	}
	
	public Product(String name, Inventory inventory) {
		this.name = name;
		this.inventory = inventory;
	}
	
	public Product(Integer id, String name, Inventory inventory) {
		this.id = id;
		this.name = name;
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

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product id: " + id + ", name: " + name + ", inventory: " + inventory;
	}
}
