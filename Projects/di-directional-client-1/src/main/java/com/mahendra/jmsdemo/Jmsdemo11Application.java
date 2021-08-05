package com.mahendra.jmsdemo;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class Jmsdemo11Application implements CommandLineRunner{
	
	@Autowired private JmsTemplate template;

	@Bean
	public MessageConverter jacksonConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	@Bean
	public JmsListenerContainerFactory<?> myFactory
		(ConnectionFactory conFactory, DefaultJmsListenerContainerFactoryConfigurer config)
	{	
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		config.configure(factory, conFactory);
		return factory;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Jmsdemo11Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FundTransfer tr = new FundTransfer("SB1010", "XYZ Bank", "SB10229", 10000);
		template.convertAndSend("sample.queue", tr);
		System.out.println("Message sent!");
	}

}
