package br.com.leomanzini.product.store.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.enums.ErrorMessages;
import br.com.leomanzini.product.store.exceptions.CsvReaderException;
import br.com.leomanzini.product.store.model.entities.Inventory;
import br.com.leomanzini.product.store.model.entities.Product;
import br.com.leomanzini.product.store.model.entities.Store;

public class StoreCsvReader {

	private static final Logger log = LogManager.getLogger(StoreCsvReader.class);

	private Store storeItens = null;

	public Store readCsv(String csvPath) throws CsvReaderException {
		String row;

		try (BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {

			log.info("Reading csv file");

			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");

				if (data[0].equals("STORE_ID")) {
					continue;
				}

				instanciateStore(data);
				Inventory inventory = instanciateInventory(data);
				Product product = instanciateProduct(data, inventory);

				verifyProductsAndInsert(storeItens.getProducts(), product);
			}

			log.info("Csv read successfully");

			return storeItens;

		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new CsvReaderException(ErrorMessages.CSV_READER_ERROR);
		}
	}

	private void instanciateStore(String[] data) {
		if (storeItens == null) {
			storeItens = new Store(Integer.parseInt(data[0]), data[1], data[2], new ArrayList<>());
		}
	}

	private Inventory instanciateInventory(String[] data) {
		Inventory inventory = new Inventory(Integer.parseInt(data[3]), Integer.parseInt(data[0]),
				Integer.parseInt(data[6]), new BigDecimal(data[5]));

		return inventory;
	}

	private Product instanciateProduct(String[] data, Inventory inventory) {
		Product product = new Product(Integer.parseInt(data[3]), data[4], inventory);

		return product;
	}

	private void verifyProductsAndInsert(List<Product> products, Product product) {
		if (!products.contains(product)) {
			products.add(product);
		}
	}
}
