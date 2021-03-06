package br.com.leomanzini.products.store.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.leomanzini.products.store.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

	private Integer storeDocument;
	private String storeName;
	private Set<ProductDto> products;
	
	public StoreDto(Store store) {
		this.storeDocument = store.getDocument();
		this.storeName = store.getName();
		this.products = new HashSet<>();
	}
}
