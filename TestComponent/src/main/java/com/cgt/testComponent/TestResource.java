package com.cgt.testComponent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json; charset=UTF-8", value = "/test")
public class TestResource {
	@Value("${valueFromConfigMap}")
	String valueFromConfigMap;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCell() {
		String response = valueFromConfigMap;
		
		return response;
	}
}
