package com.krnl32.shoppy.handler;

import com.krnl32.shoppy.dto.other.ErrorDTO;
import com.krnl32.shoppy.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException exception) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}
}
