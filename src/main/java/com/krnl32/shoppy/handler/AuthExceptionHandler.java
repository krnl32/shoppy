package com.krnl32.shoppy.handler;

import com.krnl32.shoppy.dto.other.ErrorDTO;
import com.krnl32.shoppy.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.CONFLICT.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
    }
}
