package com.springboot.restapi.restdemo.restExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionMessageHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value=NullPointerException.class)
	public final ResponseEntity<ErrorMessageHandler> handleNullPointer(NullPointerException nullPointerException) {
		ErrorMessageHandler exceptionResponse = new ErrorMessageHandler(nullPointerException.getMessage(), 
				"Please provide data without any null values");
		return new ResponseEntity<ErrorMessageHandler>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR) ;
		
	}
	
	@ExceptionHandler(value=InvalidParameterExceptionHandler.class)
	public final ResponseEntity<ErrorMessageHandler> handleInValidParameter(InvalidParameterExceptionHandler invalidParameterExceptionHandler) {
		ErrorMessageHandler exceptionResponse = new ErrorMessageHandler("Invalid parameters", 
				"Please provide valid parameters in the format: {" + 
						" moduloNumerator:{Numerator value}," + 
						" moduloDenominator:{Denominator value}" + 
						"}");
		return new ResponseEntity<ErrorMessageHandler>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR) ;
		
	}
	
	@ExceptionHandler(value=Exception.class)
	public final ResponseEntity<ErrorMessageHandler> handleInValidParameter(Exception httpException) {
		ErrorMessageHandler exceptionResponse = new ErrorMessageHandler(httpException.getMessage(), 
				"The data sent is incorrect.");
		return new ResponseEntity<ErrorMessageHandler>(exceptionResponse,HttpStatus.BAD_REQUEST) ;
		
	}

}
