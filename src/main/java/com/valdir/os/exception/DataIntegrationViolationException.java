package com.valdir.os.exception;

public class DataIntegrationViolationException extends RuntimeException {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 773514305781214696L;

	public DataIntegrationViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrationViolationException(String message) {
		super(message);
	}
}
