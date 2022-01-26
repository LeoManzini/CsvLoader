package br.com.leomanzini.products.store.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObjectDto {
	
	private String message;
	
	public ResponseObjectDto(String message) {
		this.message = message;
	}
}
