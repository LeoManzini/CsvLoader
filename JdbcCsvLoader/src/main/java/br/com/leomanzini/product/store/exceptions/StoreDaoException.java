package br.com.leomanzini.product.store.exceptions;

import br.com.leomanzini.product.store.enums.ErrorMessages;

public class StoreDaoException extends Exception {

	private static final long serialVersionUID = 4509376250083482151L;
	
	public StoreDaoException(ErrorMessages error) {
		super(error.getMessage());
	}
}
