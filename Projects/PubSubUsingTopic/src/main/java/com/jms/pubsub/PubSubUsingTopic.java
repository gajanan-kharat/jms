package com.jms.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PubSubUsingTopic {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/myTopic");
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = cf.createConnection();
		Session session = connection.createSession();
		
		MessageProducer producer = session.createProducer(topic);
		MessageConsumer consumer1 = session.createConsumer(topic);
		MessageConsumer consumer2 = session.createConsumer(topic);
		
		TextMessage message = session.createTextMessage("Publish and Subscribe messaging using Topic");
		producer.send(message);
		System.out.println("Message send to topic");
		connection.start();
		TextMessage receive1 = (TextMessage)consumer1.receive();
		TextMessage receive2 = (TextMessage)consumer2.receive();
		System.out.println("Two consumers 2, receiving same message");
		System.out.println(receive1.getText());
		System.out.println(receive2.getText());
		connection.close();
		context.close();
	}

}
