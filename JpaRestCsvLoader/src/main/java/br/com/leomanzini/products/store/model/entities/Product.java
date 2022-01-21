package br.com.leomanzini.products.store.model.entities;

import lombok.EqualsAndHashCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

	@EqualsAndHashCode.Include
	private Integer id;
	private String name;
	private Inventory inventory;
	
	public Product(String name, Inventory inventory) {
		this.name = name;
		this.inventory = inventory;
	}
}
