package com.db.org.trading.exception;

public class ClassNodeNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ClassNodeNotFoundException(Integer className) {
		super("No implementation found for the signal " + className);
	}
}
