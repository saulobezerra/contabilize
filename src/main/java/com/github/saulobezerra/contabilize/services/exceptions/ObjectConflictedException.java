package com.github.saulobezerra.contabilize.services.exceptions;

public class ObjectConflictedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectConflictedException(String msg) {
		super(msg);
	}
	
	public ObjectConflictedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
