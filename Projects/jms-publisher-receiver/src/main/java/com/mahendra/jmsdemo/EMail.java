package com.mahendra.jmsdemo;

public class EMail {
	private String to;
	private String body;
	public EMail(String to, String body) {
		super();
		this.to = to;
		this.body = body;
	}
	public EMail() {
		super();
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
