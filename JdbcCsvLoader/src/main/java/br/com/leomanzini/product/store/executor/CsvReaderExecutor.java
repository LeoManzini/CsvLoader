package br.com.leomanzini.product.store.executor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.dtos.InventoryDto;
import br.com.leomanzini.product.store.dtos.ProductDto;
import br.com.leomanzini.product.store.dtos.StoreDto;

public class CsvReaderExecutor implements Executor {

	private static final Logger log = LogManager.getLogger(CsvReaderExecutor.class);

	private List<StoreDto> csvItens;

	@Override
	public void execute(String csvPath) throws IOException {

		csvItens = new ArrayList<>();

		try (BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {
			
			String row;
			
			while ((row = csvReader.readLine()) != null) {
				
				InventoryDto inventory = new InventoryDto();
				ProductDto product = new ProductDto();
				StoreDto store = new StoreDto();
				
				String[] data = row.split(",");

				if (data[0].equals("STORE_ID")) {
					continue;
				}
				
				inventory.setId(Integer.parseInt(data[6]));
				inventory.setProductId(Integer.parseInt(data[3]));
				inventory.setAmount(Integer.parseInt(data[7]));
				
				product.setId(Integer.parseInt(data[3]));
				product.setName(data[4]);
				product.setPrice(new BigDecimal(data[5]));
				product.setStoreId(Integer.parseInt(data[0]));
				product.setInventory(inventory);
				
				store.setId(Integer.parseInt(data[0]));
				store.setName(data[1]);
				store.setDocument(data[2]);
				store.getProducts().add(product);
				
				csvItens.add(store);
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			System.exit(-1);
		}
	}

	public List<StoreDto> getCsvItens() {
		return csvItens;
	}
}
