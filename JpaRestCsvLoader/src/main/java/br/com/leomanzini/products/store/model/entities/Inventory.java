package br.com.leomanzini.products.store.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inventory {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "product_serial", nullable = false)
	private Product productSerial;
	
	@Column(name = "store_document", nullable = false)
	private Store storeDocument;
	
	private Integer amount;
	private BigDecimal price;

	public Inventory(Product productSerial, Store storeDocument, Integer amount, BigDecimal price) {
		this.productSerial = productSerial;
		this.storeDocument = storeDocument;
		this.amount = amount;
		this.price = price;
	}
}
