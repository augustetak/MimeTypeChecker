package com.example.demo.entities;

public class MimeFileInfo {
	private String name;
	private String mimeType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public MimeFileInfo(String name, String mimeType) {
		super();
		this.name = name;
		this.mimeType = mimeType;
	}
	public MimeFileInfo() {
		super();
	}
	@Override
	public String toString() {
		return "MimeFileInfo [name=" + name + ", mimeType=" + mimeType + "]";
	}
	
	

}
