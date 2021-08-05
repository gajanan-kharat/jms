package com.mahendra.jmsdemo;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
public class Jmsdemo3Application {

	@Bean
	public JmsListenerContainerFactory<?> myFactory
		(ConnectionFactory conFactory, DefaultJmsListenerContainerFactoryConfigurer config)
	{	
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		config.configure(factory, conFactory);
		return factory;
	}
	@Bean
	public MessageConverter jacksonConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
		
	public static void main(String[] args) {
		SpringApplication.run(Jmsdemo3Application.class, args);
	}

}
