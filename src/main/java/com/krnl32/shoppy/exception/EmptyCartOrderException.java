package com.krnl32.shoppy.exception;

public class EmptyCartOrderException extends RuntimeException {
	public EmptyCartOrderException(String message) {
		super(message);
	}
}
