package com.mahendra.jmsdemo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@Autowired JmsTemplate template;
	
	@JmsListener(destination="sample.queue",containerFactory="myFactory")
	public void readMessage(FundTransfer message) {
		System.out.print("Date "+ LocalDate.now());
		System.out.println(" Message : "+message.getAmount()+" from "+message.getFromAccount());
		template.convertAndSend("reply.queue","Transfer was successful!");
	}
}
