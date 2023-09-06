package com.jafa.error;

public class PasswordMisMatchException extends RuntimeException {

	public PasswordMisMatchException(String message) {
		super(message);
	}
}
