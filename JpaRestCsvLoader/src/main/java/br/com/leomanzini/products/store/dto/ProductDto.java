package br.com.leomanzini.products.store.dto;

import java.math.BigDecimal;

import br.com.leomanzini.products.store.entities.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private Integer productId;
	private String productName;
	private Integer productSerial;
	private Integer amount;
	private BigDecimal price;
	
	public ProductDto(Inventory inventory) {
		this.productId = inventory.getProduct().getId();
		this.productName = inventory.getProduct().getName();
		this.productSerial = inventory.getProduct().getSerial();
		this.amount = inventory.getAmount();
		this.price = inventory.getPrice();
	}
}
