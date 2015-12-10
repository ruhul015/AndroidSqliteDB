package com.droid.bdapp.androidsqlitedb.datasources.online;

public class APIResponse {

	private String contentTypeName;
	private String contentTypeValue;
	private String jsonString;
	private int statusCode;
	private String statusMessage;
	private boolean gotException = false;

	public APIResponse() {
	}

	public APIResponse(String contentTypeName, String contentTypeValue,
			String jsonString, int statusCode, String statusMessage,
			boolean gotException) {
		this.contentTypeName = contentTypeName;
		this.contentTypeValue = contentTypeValue;
		this.jsonString = jsonString;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.gotException = gotException;

	}

	public String getContentTypeName() {
		return contentTypeName;
	}

	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}

	public String getContentTypeValue() {
		return contentTypeValue;
	}

	public void setContentTypeValue(String contentTypeValue) {
		this.contentTypeValue = contentTypeValue;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isGotException() {
		return gotException;
	}

	public void setGotException(boolean gotException) {
		this.gotException = gotException;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
