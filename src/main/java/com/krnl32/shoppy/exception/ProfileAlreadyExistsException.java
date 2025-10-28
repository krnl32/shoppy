package com.krnl32.shoppy.exception;

public class ProfileAlreadyExistsException extends RuntimeException {
	public ProfileAlreadyExistsException(String message) {
		super(message);
	}
}
