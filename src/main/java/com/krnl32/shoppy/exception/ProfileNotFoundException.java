package com.krnl32.shoppy.exception;

public class ProfileNotFoundException extends RuntimeException {
	public ProfileNotFoundException(String message) {
		super(message);
	}
}
