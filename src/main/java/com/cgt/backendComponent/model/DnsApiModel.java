package com.cgt.backendComponent.model;

import java.util.HashMap;
import java.util.List;

public class DnsApiModel {
	private String Status;
	private List<HashMap<String, String>> Answer;

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<HashMap<String, String>> getAnswer() {
		return Answer;
	}

	public void setAnswer(List<HashMap<String, String>> answer) {
		Answer = answer;
	}
}
