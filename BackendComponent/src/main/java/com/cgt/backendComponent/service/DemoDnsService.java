package com.cgt.backendComponent.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cgt.backendComponent.config.ConfigUtils;
import com.cgt.backendComponent.model.HostIP;
import com.cgt.backendComponent.model.ResponseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class DemoDnsService {
	
	@Autowired
	ConfigUtils configUtils;
	
	public ResponseEntity<ResponseModel> resolveHostname(String hostname) throws Exception {
		URIBuilder builder = new URIBuilder(configUtils.getDnsApiEndpoint());
		builder.setParameter("name", hostname);
		
		URI url = builder.build();
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = (rt.exchange(url, HttpMethod.GET, setHeader(), String.class));
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HostIP h = gson.fromJson(response.getBody(), HostIP.class);
		
		List<String> l = new ArrayList<String>();
		
		for(Iterator<HashMap<String, String>> ans = h.getAnswer().iterator(); ans.hasNext();) {
			l.add(ans.next().get("data"));
		}
		
		ResponseModel rm = new ResponseModel(l);
		
		return new ResponseEntity<ResponseModel>(rm, HttpStatus.OK);
	}
	
	private HttpEntity<String> setHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<String>(headers);
	}
	
}