package br.com.leomanzini.products.store.exceptions;

import br.com.leomanzini.products.store.utils.SystemMessages;

public class CsvReaderException extends Exception {

	private static final long serialVersionUID = 7392728985578362360L;

	public CsvReaderException(SystemMessages csvReaderError) {
		super(csvReaderError.getMessage());
	}
}
