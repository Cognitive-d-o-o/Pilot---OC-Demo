package com.cgt.backendComponent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgt.backendComponent.model.ResponseModel;
import com.cgt.backendComponent.service.DemoDnsService;

@RestController
@RequestMapping(produces = "application/json", value = "/DNSLookup")
public class DemoDnsController {
	
	@Autowired
	DemoDnsService dds;
	
	@GetMapping("/{hostname}")
	public ResponseEntity<ResponseModel> getIpAddress(@PathVariable("hostname") String hostname) throws Exception {
		return dds.resolveHostname(hostname);
	}
}
