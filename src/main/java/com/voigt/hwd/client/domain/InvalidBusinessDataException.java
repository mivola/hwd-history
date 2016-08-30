package com.voigt.hwd.client.domain;

public class InvalidBusinessDataException extends RuntimeException {

	private static final long serialVersionUID = -8656578126921273186L;

	public InvalidBusinessDataException(String message) {
		super(message);
	}

}
