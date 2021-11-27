package br.com.leomanzini.product.store.executor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
				String[] data = row.split(",");

				if (data[0].equals("STORE_ID")) {
					continue;
				}
				
				log.info(data[0]);
				log.info(data[1]);
				log.info(data[2]);
				log.info(data[3]);
				log.info(data[4]);
				log.info(data[5]);
				log.info(data[6]);
				log.info(data[7]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<StoreDto> getCsvItens() {
		return csvItens;
	}
}
