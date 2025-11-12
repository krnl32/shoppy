package com.krnl32.shoppy.handler;

import com.krnl32.shoppy.dto.other.ErrorDTO;
import com.krnl32.shoppy.exception.EmptyCartOrderException;
import com.krnl32.shoppy.exception.PaymentException;
import com.krnl32.shoppy.exception.ResourceAlreadyExistsException;
import com.krnl32.shoppy.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
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

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception, HttpServletRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.CONFLICT.value(), exception.getMessage(), request.getRequestURI(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException exception, HttpServletRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), request.getRequestURI(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getRequestURI(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception, HttpServletRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getRequestURI(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<ErrorDTO> handlePaymentException(PaymentException exception, HttpServletRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getRequestURI(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EmptyCartOrderException.class)
	public ResponseEntity<ErrorDTO> handleEmptyCartOrderException(EmptyCartOrderException exception, HttpServletRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getRequestURI(), System.currentTimeMillis());
		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}
}
