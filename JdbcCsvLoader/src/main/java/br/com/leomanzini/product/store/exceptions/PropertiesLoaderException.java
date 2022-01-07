package br.com.leomanzini.product.store.exceptions;

import br.com.leomanzini.product.store.enums.ErrorMessages;

public class PropertiesLoaderException extends Exception {

	private static final long serialVersionUID = 4509376250083482151L;
	
	public PropertiesLoaderException(ErrorMessages error) {
		super(error.getMessage());
	}
}
