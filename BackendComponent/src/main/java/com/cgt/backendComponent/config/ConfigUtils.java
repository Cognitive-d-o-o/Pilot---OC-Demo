package com.cgt.backendComponent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("conf")
public class ConfigUtils {
	private String dnsApiEndpoint;

	public String getDnsApiEndpoint() {
		return dnsApiEndpoint;
	}

	public void setDnsApiEndpoint(String dnsApiEndpoint) {
		this.dnsApiEndpoint = dnsApiEndpoint;
	}
}
