package com.mahendra.jmsdemo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "sample.queue", containerFactory = "myFactory")
	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}
}