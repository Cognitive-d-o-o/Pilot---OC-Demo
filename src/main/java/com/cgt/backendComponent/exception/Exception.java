package com.cgt.backendComponent.exception;

public class Exception {

	private String error;
	private String code;
	
	public Exception() {
	}

	public Exception(String error, String code) {
		this.error = error;
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
