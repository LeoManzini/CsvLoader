package br.com.leomanzini.product.store.executor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.dtos.InventoryDto;
import br.com.leomanzini.product.store.dtos.ProductDto;
import br.com.leomanzini.product.store.dtos.StoreDto;

public class CsvReaderExecutor implements Executor {

	// private static final Logger log =
	// LogManager.getLogger(CsvReaderExecutor.class);

	private StoreDto storeItens = null;

	@Override
	public void execute(String csvPath) throws IOException {

		String row;
		List<ProductDto> products = new ArrayList<>();

		try (BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {
			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");

				if (data[0].equals("STORE_ID")) {
					continue;
				}

				instanciateStore(data);
				InventoryDto inventory = instanciateInventory(data);
				ProductDto product = instanciateProduct(data, inventory);

				products.add(product);
			}

			storeItens.setProducts(products);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void instanciateStore(String[] data) {
		if (storeItens == null) {
			storeItens = new StoreDto();
			storeItens.setId(Integer.parseInt(data[0]));
			storeItens.setName(data[1]);
			storeItens.setDocument(data[2]);
		}
	}

	private InventoryDto instanciateInventory(String[] data) {
		InventoryDto inventory = new InventoryDto();
		inventory.setId(Integer.parseInt(data[6]));
		inventory.setProductId(Integer.parseInt(data[3]));
		inventory.setAmount(Integer.parseInt(data[7]));

		return inventory;
	}
	
	private ProductDto instanciateProduct(String[] data, InventoryDto inventory) {
		ProductDto product = new ProductDto();
		product.setId(Integer.parseInt(data[3]));
		product.setName(data[4]);
		product.setPrice(new BigDecimal(data[5]));
		product.setStoreId(Integer.parseInt(data[0]));
		product.setInventory(inventory);
		
		return product;
	}

	public StoreDto getCsvItens() {
		return storeItens;
	}
}
