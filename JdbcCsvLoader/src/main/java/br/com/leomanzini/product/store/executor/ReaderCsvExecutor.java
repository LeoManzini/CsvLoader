package br.com.leomanzini.product.store.executor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.dto.InventoryDto;
import br.com.leomanzini.product.store.dto.ProductDto;
import br.com.leomanzini.product.store.dto.StoreDto;

public class ReaderCsvExecutor implements Executor {

	private static final Logger log = LogManager.getLogger(ReaderCsvExecutor.class);
	private StoreDto store = null;

	@Override
	public void executar(String csvPath) {

		String line;
		// List<ProductDto> listProd = new ArrayList<>();

		// Criamos dentro do try/catch,
		try (BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {
			while ((line = csvReader.readLine()) != null) {
				String[] column = line.split(",");
				if (column[0].equals("STORE_ID")) {
					continue;// sai dessa interação, porém continua o laço.
				}
				if (store == null) {
					store = new StoreDto();
					store.setId(Integer.parseInt(column[0]));
					store.setName(column[1]);
					store.setDocument(column[2]);
					store.setProducts(new ArrayList<>());
				}

				InventoryDto inventory = new InventoryDto();
				inventory.setProductId(Integer.parseInt(column[3]));
				inventory.setId(Integer.parseInt(column[6]));
				inventory.setAmount(Integer.parseInt(column[7]));
				
				ProductDto product = new ProductDto();
				product.setId(Integer.parseInt(column[3]));
				product.setName(column[4]);
				product.setPrice(new BigDecimal(column[5]));//para converter o bigdecimal em string
				product.setStoreId(store.getId());
				product.setInventoryAmount(inventory);
				
				if(!store.getProducts().contains(product) ) { 
					store.getProducts().add(product);
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	public StoreDto getStore() {
		return store;
	}

}
