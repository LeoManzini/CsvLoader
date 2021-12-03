package br.com.leomanzini.product.store.executor;

import java.io.BufferedReader;
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
import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.CsvReaderException;

public class CsvExecutor implements Executor {

	// TODO colocar a execução da classe de leitura de CVS aqui, igual a classe DAO e a execução de persistencia 
	
	private static final Logger log = LogManager.getLogger(CsvExecutor.class);

	private StoreDto storeItens = null;

	@Override
	public void execute(String csvPath) throws CsvReaderException {

		String row;

		try (BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {
			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");
				
				if (data[0].equals("STORE_ID")) {
					continue;
				}

				instanciateStore(data);
				InventoryDto inventory = instanciateInventory(data);
				ProductDto product = instanciateProduct(data, inventory);

				verifyProductsAndInsert(storeItens.getProducts(), product);
			}

		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new CsvReaderException(ErrorMessages.CSV_READER_ERROR);
		}
	}

	private void instanciateStore(String[] data) {
		if (storeItens == null) {
			storeItens = new StoreDto(Integer.parseInt(data[0]), data[1], data[2], new ArrayList<>());
		}
	}

	private InventoryDto instanciateInventory(String[] data) {
		InventoryDto inventory = new InventoryDto(Integer.parseInt(data[6]), Integer.parseInt(data[3]),
				Integer.parseInt(data[7]));

		return inventory;
	}

	private ProductDto instanciateProduct(String[] data, InventoryDto inventory) {
		ProductDto product = new ProductDto(Integer.parseInt(data[3]), data[4], new BigDecimal(data[5]),
				storeItens.getId(), inventory);

		return product;
	}

	private void verifyProductsAndInsert(List<ProductDto> products, ProductDto product) {
		if (!products.contains(product)) {
			products.add(product);
		}
	}

	public StoreDto getCsvItens() {
		return storeItens;
	}
}
