package com.nithish.ewt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EWTException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(EWTException ex){
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);
	
	}
}
