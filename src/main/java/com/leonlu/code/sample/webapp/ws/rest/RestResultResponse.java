package com.leonlu.code.sample.webapp.ws.rest;


public class RestResultResponse {
	private Boolean status;
	private Object result;

	public RestResultResponse(Boolean status) {
		this.status = status;
	}
	
	public RestResultResponse(Boolean status, Object result) {
		this.status = status;
		this.result = result;
	}

	public Boolean getStatus() {
		return status;
	}

	public Object getResult() {
		return result;
	}
}
