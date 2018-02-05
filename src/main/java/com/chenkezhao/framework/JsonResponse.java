package com.chenkezhao.framework;

import java.io.Serializable;

public class JsonResponse implements Serializable {
	protected boolean success;
	protected String message;
	protected Object data;

	public JsonResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JsonResponse(boolean success, String message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
