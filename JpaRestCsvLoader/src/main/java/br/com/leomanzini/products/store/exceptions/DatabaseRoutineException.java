package br.com.leomanzini.products.store.exceptions;

import br.com.leomanzini.products.store.utils.SystemMessages;

public class DatabaseRoutineException extends Exception {

	private static final long serialVersionUID = 2759123573356817560L;

	public DatabaseRoutineException(SystemMessages routineExecutionException) {
		super(routineExecutionException.getMessage());
	}
}
