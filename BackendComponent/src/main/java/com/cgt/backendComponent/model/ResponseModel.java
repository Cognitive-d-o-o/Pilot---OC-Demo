package com.cgt.backendComponent.model;

import java.util.List;

public class ResponseModel {
	private List<String> addresses;
	
	public ResponseModel(List<String> addresses) {
		super();
		this.addresses = addresses;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	
	
}
