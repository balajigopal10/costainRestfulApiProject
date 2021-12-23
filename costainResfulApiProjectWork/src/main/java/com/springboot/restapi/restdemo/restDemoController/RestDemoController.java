package com.springboot.restapi.restdemo.restDemoController;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springboot.restapi.restdemo.restExceptionHandler.InvalidParameterExceptionHandler;
import com.springboot.restapi.restdemo.restJson.RestDemoLayout;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/restDemoController")
public class RestDemoController {
	
	RestDemoLayout jsonLayout = new RestDemoLayout();
	
	@PostMapping("/stringConcatenation")
	@Operation(summary = "Will concatenate a list of strings sent in the request")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "String Concatenation successful",
					content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "String Concatenation failure",
			content= {@Content(mediaType = "application/json")})
	})
	public RestDemoLayout stringConcatenation(@RequestBody List<String> concatStringList) 
	{
		String resultConcatString = "";
				
		for(String concatString : concatStringList) 
		{
			if (Optional.ofNullable(concatString).isPresent())
			{
				resultConcatString = resultConcatString.concat(concatString);
			}
		}
		
		jsonLayout.setOperationPerformed("String Concatenation");
		jsonLayout.setValue(resultConcatString);
		return jsonLayout;
	}
	
	@PostMapping("/floatAddition")
	@Operation(summary = "Sums up the list of floating point numbers sent in the request")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Float addition successful",
					content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "Float addition failure",
			content= {@Content(mediaType = "application/json")})
	})
	public RestDemoLayout floatAddition(@RequestBody List<Double> addFloatList) 
	{
		Double resultFloatValue = 0.0;
		int size = 0;
		
		for(Double addFloat : addFloatList) 
		{
			String addFloatString = addFloat.toString();
			int tempSize = 0;
			String[] addFloatArray = addFloatString.split("\\.");
			
			if(addFloatArray.length > 0) 
			{
				tempSize = addFloatArray[1].length();
				
				if(tempSize > size) 
				{
					size = tempSize;
				}
			}
			
			resultFloatValue = resultFloatValue + addFloat;
		}
		
		jsonLayout.setOperationPerformed("Float Addition");
		jsonLayout.setValue(Double.valueOf(String.format("%."+ size +"f", resultFloatValue)).toString());
		return jsonLayout;

	}
	
	@PostMapping("/moduloOperation")
	@Operation(summary = "Returns the signed remainder of division for the two numbers sent in the request")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Modulo operation successful",
					content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "Modulo operation failure",
			content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "500", description = "Please provide valid parameters",
			content= {@Content(mediaType = "application/json")})
	})
	@NonNull
	public RestDemoLayout moduloOperation(@RequestBody ObjectNode objectNode) 
	{
		double moduloResult = 0.0;
		double moduloNumerator;
		double moduloDenominator;
		
		if(Optional.ofNullable(objectNode.get("moduloNumerator")).isPresent() && 
				Optional.ofNullable(objectNode.get("moduloDenominator")).isPresent()) 
		{
			moduloNumerator = Double.parseDouble(objectNode.get("moduloNumerator").asText());
			moduloDenominator = Double.parseDouble(objectNode.get("moduloDenominator").asText());	
			try 
			{
				moduloResult = moduloNumerator% moduloDenominator;
				if (Double.isNaN(moduloResult)) 
				{
					moduloResult = 0.0;
				}
				
			}
			catch (ArithmeticException ex) 
			{
				moduloResult = 0.0;
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			jsonLayout.setValue(Double.toString(moduloResult));
		}
		else 
		{
			throw new InvalidParameterExceptionHandler();
		}
		
		jsonLayout.setOperationPerformed("Modulo Operation");
		return jsonLayout;
		
	}
}