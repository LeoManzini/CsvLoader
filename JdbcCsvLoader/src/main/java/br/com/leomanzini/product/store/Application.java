package br.com.leomanzini.product.store;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.connector.PostgresConnector;
import br.com.leomanzini.product.store.executor.Executor;
import br.com.leomanzini.product.store.executor.ReaderCsvExecutor;

public class Application {

	private static final Logger log = LogManager.getLogger(Application.class); 
	
	public static void main(String[] args) {
		
		log.info("Hello World!!");
		
		log.info("Leo, agora vai");
		
		if(args.length != 2) { 
			log.error("Wrong arguments Number ");
			System.exit(-1);
		} 
		String argProper = args[0]; 
		String argCsv = args[1]; 
		
		Executor csvExecutor = new ReaderCsvExecutor();
		
		csvExecutor.executar(argCsv);
	
		log.info(((ReaderCsvExecutor) csvExecutor).getStore());
		
	}
}
