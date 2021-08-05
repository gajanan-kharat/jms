package com.mahendra.jmsdemo;

import java.util.Scanner;

import javax.jms.JMSConnectionFactory;
import javax.jms.JMSDestinationDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
public class JmsPublisherApplication implements CommandLineRunner {

	  @Bean // Serialize message content to json using TextMessage
	  public MessageConverter jacksonJmsMessageConverter() {
	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	    converter.setTargetType(MessageType.TEXT);
	    converter.setTypeIdPropertyName("_type");
	    return converter;
	  }

	
	@Autowired private JmsTemplate template;
	public static void main(String[] args) {
		SpringApplication.run(JmsPublisherApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter message details : ");
		System.out.print("To : ");
		String to = sc.nextLine();
		System.out.println("Body : ");
		String body = sc.nextLine();
		Email email = new Email(to, body);
		template.convertAndSend("sample.queue",email);
		
	}

}
