package com.mahendra.jmsdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class JmsDemo2Application implements CommandLineRunner {

	@Autowired private JmsTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(JmsDemo2Application.class, args);
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Override
	public void run(String... args) throws Exception {
		FundTransfer tr = new FundTransfer("SB10294", "PQR Bank", "12399133", 21000.0);
		template.convertAndSend("sample",tr);
		System.out.println("Message sent !");
	}

}
