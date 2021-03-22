package com.cgt.backendComponent.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cgt.backendComponent.configUtils.ConfigUtils;
import com.cgt.backendComponent.helper.CallDnsResolverAPI;
import com.cgt.backendComponent.model.IPAddressModel;

@Service
public class DnsResolverService {
	
	public ResponseEntity<IPAddressModel> getIP(Optional<String> hostname, ConfigUtils config, UUID loggerID) {
		IPAddressModel addressList = CallDnsResolverAPI.callDnsApi(hostname, config, loggerID);
		
		return new ResponseEntity<IPAddressModel>(addressList, HttpStatus.OK);
	}
}