package com.mahendra.jmsdemo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

@Component
public class JmsReader extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("{{input.queue}}").log(LoggingLevel.DEBUG, "New message received").process(exchange -> {
			System.out.println("Original Message was " + exchange.getMessage().getBody());
			String replyMessage = "Thanks for your message (" 
					+ exchange.getMessage().getBody() 
					+ ") We would get back to you!";
			exchange.getMessage().setBody(replyMessage);
		}).to("{{output.queue}}").log(LoggingLevel.DEBUG, "Reply Sent!").end();
		
	}

}
