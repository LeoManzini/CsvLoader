package br.com.leomanzini.product.store.exceptions;

import br.com.leomanzini.product.store.enums.ErrorMessages;

public class CsvReaderException extends Exception {

	private static final long serialVersionUID = 4509376250083482151L;
	
	public CsvReaderException(ErrorMessages error) {
		super(error.getMessage());
	}
}
