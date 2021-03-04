package com.cgt.backendComponent.configUtils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("conf")
public class ConfigUtils {
	
	private String key;
	private String format;
	private String openCellAPIUrl;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getOpenCellAPIUrl() {
		return openCellAPIUrl;
	}
	public void setOpenCellAPIUrl(String openCellAPIUrl) {
		this.openCellAPIUrl = openCellAPIUrl;
	}
	
	
	
	

}
