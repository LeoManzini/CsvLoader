package br.com.leomanzini.products.store.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {

	private Integer id;
	private Integer product;
	private Integer store;
	private Integer amount;
	private BigDecimal price;
}
