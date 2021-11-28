package br.com.leomanzini.product.store.executor;

import br.com.leomanzini.product.store.dtos.StoreDto;

public class PersistenceExecutor implements Executor {
	
	private StoreDto storeItens;

	public PersistenceExecutor(StoreDto storeItens) {
		this.storeItens = storeItens;
	}
	
	@Override
	public void execute(String propertiesPath) {
		storeItens.getName();
		
	}
}
