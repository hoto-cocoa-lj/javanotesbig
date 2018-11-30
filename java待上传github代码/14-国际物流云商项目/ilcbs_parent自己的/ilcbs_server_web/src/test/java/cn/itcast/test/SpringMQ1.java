package cn.itcast.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(value = "classpath:applicationContext.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringMQ1 {
	@Autowired
	@Qualifier(value="jmsQueueTemplate")
	private JmsTemplate jq;
	@Autowired
	@Qualifier(value="jmsTopicTemplate")
	private JmsTemplate jt;
	@Test
	public void test1() {
		MessageCreator m=new MessageCreator() {			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage m1= session.createTextMessage("fuck spring queue");
				return m1;
			}
		};
//		for(int i=0;i<9;i++){
			jq.send("itcast_spring_queue",m);
//			while(true);
//		}
	}
	@Test
	public void test2() throws JMSException {
		TextMessage m = (TextMessage)jq.receive("itcast_spring_queue");
		System.out.println(m.getText());
	}
	@Test
	public void test3() throws JMSException {
		while(true);
	}
	
	@Test
	public void test1t() {
		MessageCreator m=new MessageCreator() {			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage m1= session.createTextMessage("fuck spring topic");
				return m1;
			}
		};
		for(int i=0;i<9;i++){
			jt.send("itcast_spring_topic",m);
		}
		while(true);
	}
	@Test
	public void test2t() throws JMSException {
		TextMessage m = (TextMessage)jt.receive("itcast_spring_topic");
		System.out.println(m.getText());
	}

}
