package com.mahendra.jmsdemo;

import java.time.LocalDate;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	@JmsListener(destination="sample")
	public void receive(FundTransfer message) {
		System.out.print("DateTime: "+LocalDate.now());
		System.out.println(" Got new message: "+message.getFromBank());
		System.out.println("Transferring Rs "+message.getAmount()+" into "+message.getToAccount());
	}
}
