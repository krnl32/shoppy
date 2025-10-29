package com.krnl32.shoppy.handler;

import com.krnl32.shoppy.dto.other.ErrorDTO;
import com.krnl32.shoppy.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryExceptionHandler {
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleCategoryNotFoundException(CategoryNotFoundException exception) {
		var errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}
}
