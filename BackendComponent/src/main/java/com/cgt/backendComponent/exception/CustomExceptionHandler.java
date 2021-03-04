package com.cgt.backendComponent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = { InternalServerError.class })
	public ResponseEntity<Object> handleInternalServerError(InternalServerError e) {
		Exception internalServerErrorException = new Exception(e.getMessage(), e.getCode());
		return new ResponseEntity<Object>(internalServerErrorException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
