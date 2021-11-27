package br.com.leomanzini.product.store.executor;

import java.util.List;

public class CsvReaderExecutor implements Executor {
	
	private List<Object> csvItens;

	@Override
	public void execute(String csvPath) {
		
	}
	
	public List<Object> getCsvItens() {
		return csvItens;
	}
}
