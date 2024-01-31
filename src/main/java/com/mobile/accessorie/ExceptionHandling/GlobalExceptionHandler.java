package com.mobile.accessorie.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFOundException.class)
	public ResponseEntity<ApiResopnce> resourctionNotfoundExeception(ResourceNotFOundException ex) {

		String message = ex.getMessage();

		ApiResopnce apiResopnce = new ApiResopnce(message, false);

		return new ResponseEntity<ApiResopnce>(apiResopnce, HttpStatus.NOT_FOUND);
	}



    
}
