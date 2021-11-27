package br.com.leomanzini.product.store.executor;

import java.util.List;

import br.com.leomanzini.product.store.dtos.ProductDto;

public class PersistenceExecutor implements Executor {
	
	private List<ProductDto> csvItens;

	public PersistenceExecutor(List<ProductDto> csvItens) {
		this.csvItens = csvItens;
	}
	
	@Override
	public void execute(String propertiesPath) {
		csvItens.get(0);
		
	}
}
