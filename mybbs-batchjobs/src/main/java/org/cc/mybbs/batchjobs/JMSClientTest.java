package org.cc.mybbs.batchjobs;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JMSClientTest {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-jms.xml");

		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
		for(int i = 0; i < 10; i++){
			jmsTemplate.send(new MessageCreator() {

				public Message createMessage(Session session) throws JMSException {
					MapMessage mm = session.createMapMessage();
					mm.setString("countTemplate", new Date().toString() );
					return mm;
				}

			});
			System.out.println("Send the " + i +" message." );
			Thread.sleep(3000);
		}
		System.out.println("over....");
		
	}
}
