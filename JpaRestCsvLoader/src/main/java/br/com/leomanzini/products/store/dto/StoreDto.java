package br.com.leomanzini.products.store.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.leomanzini.products.store.model.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

	private Integer storeId;
	private String storeName;
	private Integer storeDocument;
	private List<ProductDto> products;
	
	public StoreDto(Store store) {
		this.storeId = store.getId();
		this.storeName = store.getName();
		this.storeDocument = store.getDocument();
		this.products = new ArrayList<>();
	}
}
