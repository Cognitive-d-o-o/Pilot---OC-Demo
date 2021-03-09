package com.cgt.backendComponent.model;

import java.util.List;

public class IPAddressModel {
	private List<String> addresses;

	public IPAddressModel() {
		// TODO Auto-generated constructor stub
	}

	public IPAddressModel(List<String> addresses) {
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
