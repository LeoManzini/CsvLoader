package br.com.leomanzini.product.store.executor;

import java.util.List;

public class PersistenceExecutor implements Executor {
	
	private List<Object> csvItens;

	public PersistenceExecutor(List<Object> csvItens) {
		this.csvItens = csvItens;
	}
	
	@Override
	public void execute(String propertiesPath) {
		csvItens.get(0);
		
	}
}
