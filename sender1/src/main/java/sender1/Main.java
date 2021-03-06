package sender1;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {
	
public static void main(String[] args) {
		
		/*(if(args.length == 0) {
			System.out.println("Must supply a message");
			System.out.println("From gradle run with: pom -Pargs=\"Hello World\" run");
			return ;
		}else {
			System.out.println(args[0]);
		}
		*/
		ConnectionFactory connectionFactory = null;
		javax.jms.Connection connection = null;
		Queue queue = null;
		
		try {
			InitialContext intialContext = new InitialContext();
			queue = (Queue) intialContext.lookup("JSQueue");
			connectionFactory =  
					(ConnectionFactory) intialContext.lookup("jms/__defaultConnectionFactory");
			/*
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage textMessage = session.createTextMessage("spandhana");
			messageProducer.send(textMessage);
			System.out.println("Message Produced");
			*/
			
		} catch (NamingException e) {
			e.printStackTrace();
		} /*catch (JMSException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}*/
		
		try(JMSContext context=connectionFactory.createContext()){
			
			TextMessage textMessage=context.createTextMessage("hello");
			context.createProducer().send(queue, textMessage);
			System.out.println("Message Produced");
			
		}
		
	}



}
