/**
 * 
 */
package com.activemq.tutorial;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author pramod.simikeri
 * 
 */
public class Consumer {

	/**
	 * @param args
	 * @throws JMSException
	 */

	static String QUEUE_NAME = "TEST-QUEUE";
	static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(QUEUE_NAME);
		
		MessageConsumer consumer = session.createConsumer(destination);
		Message message = consumer.receive();
		
		if(message instanceof TextMessage){
			TextMessage textMessage = (TextMessage)message;
			
			System.out.println("Recieved message "+ textMessage.getText() +" from the queue "+QUEUE_NAME);
		}
		connection.close();
	}
}
