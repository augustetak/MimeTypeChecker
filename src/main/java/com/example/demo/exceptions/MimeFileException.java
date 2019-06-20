package com.example.demo.exceptions;

public class MimeFileException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MimeFileException(String message) {
		super(message);
	}
	
	public MimeFileException() {
		super("Directory not found");
	}

}
