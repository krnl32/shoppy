package com.krnl32.shoppy.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", errors);
        error.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.badRequest().body(error);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDTO> handleException(Exception exception) {
//        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
//        return ResponseEntity.badRequest().body(error);
//    }
}
