package com.mobile.accessorie.ExceptionHandling;

public class DuplicateEmailException extends RuntimeException {

	public DuplicateEmailException(String message) {
		super(message);
	}

}
