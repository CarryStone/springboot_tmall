package com.example.demo.util;

public class Result {
	
	public static final int SUCCESS_CODE = 0;
	public static final int FAILURE_CODE = 1;
	
	private int code;
	private String message;
	
	public Result(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
