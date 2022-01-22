package br.com.leomanzini.products.store.model.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Store {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(unique = true, nullable = false)
	private Integer document;
	
	@ManyToMany
	@JoinTable(name = "inventory",
			   joinColumns = @JoinColumn(name = "store_document"),
			   inverseJoinColumns = @JoinColumn(name = "product_serial"))
	private List<Product> products;
	
	public Store(String name, Integer document, List<Product> products) {
		this.name = name;
		this.document = document;
		this.products = products;
	}
}
