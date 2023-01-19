package com.valdir.os.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1969543991318629071L;

	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

	public ValidationError(Long timeStamp, Integer status, String messages, List<FieldMessage> errors) {
		super(timeStamp, status, messages);
		this.errors = errors;
	}

	public ValidationError() {
		super();
	}

	public ValidationError(Long timeStamp, Integer status, String messages) {
		super(timeStamp, status, messages);
	}

}
