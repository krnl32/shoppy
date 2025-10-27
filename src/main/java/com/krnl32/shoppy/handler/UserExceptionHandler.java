package com.krnl32.shoppy.handler;

import com.krnl32.shoppy.dto.ErrorDTO;
import com.krnl32.shoppy.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException exception) {
        var errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
