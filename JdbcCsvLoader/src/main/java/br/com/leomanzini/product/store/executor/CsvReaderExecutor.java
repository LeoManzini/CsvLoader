package br.com.leomanzini.product.store.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.leomanzini.product.store.dtos.ProductDto;

public class CsvReaderExecutor implements Executor {
	
	private List<ProductDto> csvItens;

	@Override
	public void execute(String csvPath) throws IOException {
		
		csvItens = new ArrayList<>();
		
//		try(BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {
//			String row;
//			
//			while ((row = csvReader.readLine()) != null) {
//			    String[] data = row.split(",");
//			    
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
	public List<ProductDto> getCsvItens() {
		return csvItens;
	}
}
