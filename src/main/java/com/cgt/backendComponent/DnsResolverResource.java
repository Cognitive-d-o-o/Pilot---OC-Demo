package com.cgt.backendComponent;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cgt.backendComponent.configUtils.ConfigUtils;
import com.cgt.backendComponent.exception.InternalServerError;
import com.cgt.backendComponent.helper.CustomLogger;
import com.cgt.backendComponent.model.IPAddressModel;
import com.cgt.backendComponent.service.DnsResolverService;

@RestController
@RequestMapping(produces = "application/json", value = "/resolveHostname")
public class DnsResolverResource {
	
	@Autowired
	DnsResolverService dds;
	
	@Autowired
	ConfigUtils config;
	
	@GetMapping()
	public ResponseEntity<IPAddressModel> getIpAddress(@RequestParam Optional<String> hostname) throws Exception {
		UUID loggerID = UUID.randomUUID();
		if(!hostname.isPresent()) {
			throw new InternalServerError("hostname parameter must be provided!", "1");
		}
		
		CustomLogger.formatLogMessage("INFO", loggerID, "DnsResolverResource", "getIP", "Get IPAddress API invoked");
		
		ResponseEntity<IPAddressModel> resolvedIP = dds.getIP(hostname, config, loggerID);
		
		return resolvedIP;
	}
}
