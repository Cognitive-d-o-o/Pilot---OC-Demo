package com.cgt.backendComponent.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CellModel {

	private String lat;
	private String lon;
	private String message;
	private String error;
	private String code;

	
	public CellModel() {
		// TODO Auto-generated constructor stub
	}

	public CellModel(String lat, String lon, String message, String error, String code) {
		this.lat = lat;
		this.lon = lon;
		this.message = message;
		this.error = error;
		this.code = code;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
	 
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

}
