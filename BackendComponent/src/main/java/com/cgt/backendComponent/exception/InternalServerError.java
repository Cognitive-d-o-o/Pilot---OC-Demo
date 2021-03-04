package com.cgt.backendComponent.exception;

public class InternalServerError extends RuntimeException {

	private String code;


	public InternalServerError(String message, String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
