package com.example.Covid19.exception;

public class CSVnotfound extends Exception {

	private static final long serialVersionUID = 1L;
	private String error="CSV file not found";
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
