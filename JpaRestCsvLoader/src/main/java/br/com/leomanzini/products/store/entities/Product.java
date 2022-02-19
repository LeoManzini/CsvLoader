package br.com.leomanzini.products.store.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.leomanzini.products.store.dto.ProductDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@SuppressWarnings("serial")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {

	@Id
	@EqualsAndHashCode.Include
	private Integer serial;

	@Column(name = "nome")
	private String name;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<Inventory> inventory = new ArrayList<>();

	public Product(ProductDto productToInsert) {
		this.serial = productToInsert.getProductSerial();
		this.name = productToInsert.getProductName();
	}
}
