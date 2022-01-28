package br.com.leomanzini.products.store.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDto {

	private Integer storeId;
	private String storeName;
	private Integer storeDocument;
	private List<ProductDto> products;
}
