package com.mahendra.jmsdemo;

public class Email {
	private String to;
	private String body;
	public Email(String to, String body) {
		super();
		this.to = to;
		this.body = body;
	}
	public Email() {
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
