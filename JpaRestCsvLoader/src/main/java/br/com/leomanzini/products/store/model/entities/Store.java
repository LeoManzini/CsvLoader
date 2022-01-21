package br.com.leomanzini.products.store.model.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Store {

	@EqualsAndHashCode.Include
	private Integer id;
	private String name;
	private Integer document;
	private List<Product> products;
	
	public Store(String name, Integer document, List<Product> products) {
		this.name = name;
		this.document = document;
		this.products = products;
	}
}
