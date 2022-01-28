package br.com.leomanzini.products.store.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

	private Integer productId;
	private String productName;
	private Long productSerial;
	private Integer amount;
	private BigDecimal price;
}
