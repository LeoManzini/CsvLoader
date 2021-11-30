package br.com.leomanzini.product.store.connector;

import java.sql.Connection;

public interface DataBaseConnector {

	public Connection startDataBaseConnection(String propriedadesPath);
}
