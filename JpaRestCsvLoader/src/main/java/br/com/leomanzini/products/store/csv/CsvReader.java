package br.com.leomanzini.products.store.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.products.store.dto.ProductDto;
import br.com.leomanzini.products.store.dto.StoreDto;
import br.com.leomanzini.products.store.exceptions.CsvReaderException;
import br.com.leomanzini.products.store.utils.SystemMessages;

public class CsvReader {

	private static final Logger log = LogManager.getLogger(CsvReader.class);
	
	private StoreDto storeItens = null;

	public StoreDto readCsv(String csvPath) throws CsvReaderException {
		String row;

		try (BufferedReader csvReader = new BufferedReader(new FileReader(csvPath))) {

			log.info("Reading csv file");

			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");

				if (data[0].equals("STORE_NAME")) {
					continue;
				}

				instanciateStore(data);
				ProductDto product = instanciateProduct(data);

				verifyProductsAndInsert(storeItens.getProducts(), product);
			}

			log.info("Csv read successfully");

			return storeItens;

		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new CsvReaderException(SystemMessages.CSV_READER_ERROR);
		}
	}

	private void instanciateStore(String[] data) {
		if (storeItens == null) {
			storeItens = StoreDto.builder().storeName(data[0]).storeDocument(Integer.parseInt(data[1]))
					.products(new ArrayList<>()).build();
		}
	}

	private ProductDto instanciateProduct(String[] data) {
		return ProductDto.builder().productSerial(Integer.parseInt(data[2])).productName(data[3])
				.amount(Integer.parseInt(data[5])).price(new BigDecimal(data[4])).build();
	}

	private void verifyProductsAndInsert(List<ProductDto> products, ProductDto product) {
		if (!products.contains(product)) {
			products.add(product);
		}
	}
}
