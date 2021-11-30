package br.com.leomanzini.product.store.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.leomanzini.product.store.utils.ProperLoader;

public class PostgresConnector implements DataBaseConnector {

	private static final Logger log = LogManager.getLogger(PostgresConnector.class);
	private Connection conexaoPostgress;

	@Override
	public Connection startDataBaseConnection(String propriedadesPath) {
		//verifca se já temos uma conexao ou cria uma nova.
		if (conexaoPostgress == null) {
			ProperLoader.carregaPropriedadesBanco(propriedadesPath);
			try {
				conexaoPostgress = DriverManager.getConnection(ProperLoader.getDatabaseUrl(), ProperLoader.getDatabaseUser(), ProperLoader.getDatabasePassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
			}
		}
		return conexaoPostgress;
	}
}
