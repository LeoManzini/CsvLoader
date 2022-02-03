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

	private Integer productSerial;
	private String productName;
	private Integer amount;
	private BigDecimal price;
	
	public ProductDto(Inventory inventory) {
		this.productSerial = inventory.getProduct().getSerial();
		this.productName = inventory.getProduct().getName();
		this.amount = inventory.getAmount();
		this.price = inventory.getPrice();
	}
}
