package com.krnl32.shoppy.handler;

import com.krnl32.shoppy.dto.other.ErrorDTO;
import com.krnl32.shoppy.exception.ProfileAlreadyExistsException;
import com.krnl32.shoppy.exception.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProfileExceptionHandler {
	@ExceptionHandler(ProfileNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleProfileNotFoundException(ProfileNotFoundException exception) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProfileAlreadyExistsException.class)
	public ResponseEntity<ErrorDTO> handleProfileAlreadyExistsException(ProfileAlreadyExistsException exception) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.CONFLICT.value(), exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
	}
}
