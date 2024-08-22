package com.taskboard.management.response;

public class TaskBoardResponsePojo {

	private String message;
	private Object responseData;
	private Boolean isSuccess;

	public void response(String message, Object responseData, boolean isSuccess) {
		setMessage(message);
		setResponseData(responseData);
		setIsSuccess(isSuccess);
	}
	
	public TaskBoardResponsePojo errorResponse(String message) {
		response(message, null, false);
		return this;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
