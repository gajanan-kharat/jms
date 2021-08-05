package com.jms.p2p;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class P2PUsingQueueJMS2_X {
	public static void main(String[] args) throws NamingException {

		InitialContext initialContext = new InitialContext();
		Queue queue = (Queue) initialContext.lookup("queue/myQueue");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {
			jmsContext.createProducer().send(queue, "Arise, Awake and stop not till the goal is reached!");
			String message = jmsContext.createConsumer(queue).receiveBody(String.class);
			System.out.println(message);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
