package com.example.demo.utils;

public class MimeChecked {
	String fileName;
	boolean validaded;
	public MimeChecked() {
		super();
	}
	public MimeChecked(String fileName, boolean validaded) {
		super();
		this.fileName = fileName;
		this.validaded = validaded;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isValidaded() {
		return validaded;
	}
	public void setValidaded(boolean validaded) {
		this.validaded = validaded;
	}
	@Override
	public String toString() {
		return "MimeChecked [fileName=" + fileName + ", validaded=" + validaded + "]";
	}
	
	

}
