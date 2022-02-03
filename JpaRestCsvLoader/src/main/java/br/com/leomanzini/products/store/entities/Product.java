package br.com.leomanzini.products.store.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.leomanzini.products.store.dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "products")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "serial", unique = true, nullable = false)
	private Integer serial;
	
	@Builder.Default
	@OneToMany(mappedBy = "product")
	private List<Inventory> inventory = new ArrayList<>();

	public Product(ProductDto productToInsert) {
		this.name = productToInsert.getProductName();
		this.serial = productToInsert.getProductSerial();
	}
}
