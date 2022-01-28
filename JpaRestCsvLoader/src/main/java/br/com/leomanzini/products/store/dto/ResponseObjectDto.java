package br.com.leomanzini.products.store.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObjectDto {
	
	private String message;
	
	@Builder.Default
	private LocalDateTime time = LocalDateTime.now();
}
