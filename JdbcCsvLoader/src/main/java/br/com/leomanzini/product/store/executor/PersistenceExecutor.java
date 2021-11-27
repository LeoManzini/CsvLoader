package br.com.leomanzini.product.store.executor;

import java.util.List;

import br.com.leomanzini.product.store.dtos.StoreDto;

public class PersistenceExecutor implements Executor {
	
	private List<StoreDto> csvItens;

	public PersistenceExecutor(List<StoreDto> csvItens) {
		this.csvItens = csvItens;
	}
	
	@Override
	public void execute(String propertiesPath) {
		csvItens.get(0);
		
	}
}
