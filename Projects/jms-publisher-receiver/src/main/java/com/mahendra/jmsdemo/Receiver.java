package com.mahendra.jmsdemo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(EMail email) {
		System.out.println("Received <" + email + ">");
	}

}