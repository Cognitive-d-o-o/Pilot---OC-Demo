package com.cgt.backendComponent.model;

import java.util.List;

public class IPAddressModel {
	private List<String> IpAddress;

	public IPAddressModel() {
		// TODO Auto-generated constructor stub
	}

	public IPAddressModel(List<String> IpAddress) {
		super();
		this.IpAddress = IpAddress;
	}

	public List<String> getAddresses() {
		return IpAddress;
	}

	public void setAddresses(List<String> IpAddress) {
		this.IpAddress = IpAddress;
	}
	
	
}
