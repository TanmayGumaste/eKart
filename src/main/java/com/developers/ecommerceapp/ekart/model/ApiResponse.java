package com.developers.ecommerceapp.ekart.model;

import java.time.LocalDateTime;

public class ApiResponse {
	private final boolean success;
	private final String message;
	private final Integer id;
	// try again
	
	public ApiResponse(boolean success, String message,Integer id) {
		this.success = success;
		this.message = message;
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
	
	public Integer getId() {
		return id;
	}
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}
}
