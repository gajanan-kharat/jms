package com.mahendra.jmsdemo;

import java.time.LocalDate;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@JmsListener(destination="reply.queue",containerFactory="myFactory")
	public void readMessage(String message) {
		System.out.println("Reply receied : "+message);
	}
}
