package com.springboot.restapi.restdemo.restJson;

public class RestDemoLayout {

	private String operationPerformed;
	private String value;
	
	public String getOperationPerformed() 
	{
		return operationPerformed;
	}
	
	public void setOperationPerformed(String operationPerformed) 
	{
		this.operationPerformed = operationPerformed;
	}
	
	public String getValue() 
	{
		return value;
	}
	
	public void setValue(String value) 
	{
		this.value = value;
	}
	
	@Override
	public String toString() 
	{
		return "RestDemoLayout [operationPerformed=" + operationPerformed + ", value=" + value + "]";
	}
			
}