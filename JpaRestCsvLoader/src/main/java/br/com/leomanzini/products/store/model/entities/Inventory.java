package br.com.leomanzini.products.store.model.entities;

import java.math.BigDecimal;

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
public class Inventory {

	@EqualsAndHashCode.Include
	private Integer id;
	private Integer productSerie;
	private Integer productId;
	private Integer storeDocument;
	private Integer amount;
	private BigDecimal price;

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
}
