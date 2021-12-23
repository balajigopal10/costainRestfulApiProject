package com.springboot.restapi.restdemo.restExceptionHandler;

public class ErrorMessageHandler {

	private String message;
	private String details;

	public ErrorMessageHandler(String message,String details) {
		super();
		this.message = message;
		this.details = details;
	}
	
	public ErrorMessageHandler() {
		
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}

}
