package br.com.leomanzini.product.store.executor;

import java.io.IOException;
import java.util.List;

public class CsvReaderExecutor implements Executor {
	
	private List<Object> csvItens;

	@Override
	public void execute(String csvPath) throws IOException {
		
//		csvItens = new ArrayList<>();
//		
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
	
	public List<Object> getCsvItens() {
		return csvItens;
	}
}
