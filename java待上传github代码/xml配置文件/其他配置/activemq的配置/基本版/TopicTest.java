package cn.me;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;

public class TopicTest {

	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory a = new ActiveMQConnectionFactory();
		Connection c = a.createConnection();
		c.start();
		// 第1个参数表示是否使用事务，如果不使用事务下面就不需要commit
		// 第2个参数表示应答模式,一般选第一个自动应答，第二个是手动，后两个不知道
		Session s = c.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic myTopic = s.createTopic("myTopic");
		MessageProducer producer = s.createProducer(myTopic);
		// 使用MapMessage进行测试
		for (int i = 0; i < 5; i++) {
			MapMessage mm = s.createMapMessage();
			mm.setString("my", "topic" + i);
			producer.send(mm);
		}
		s.commit();
		s.close();
		c.close();
	}

	//手动接收topic消息
	@Test
	public void testTopicReceiver() throws JMSException {
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic myTopic = s.createTopic("myTopic");
		MessageConsumer cu = s.createConsumer(myTopic);
		TextMessage message = (TextMessage) cu.receive();
		System.out.println(message.getText());
		s.commit();
		/*
		 * s.close(); c.close();
		 */
		while (true)
			;
	}

	//监听器接受消息，下一个方法和这个一样
	@Test
	public void testTopicListener() throws JMSException {
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic myTopic = s.createTopic("myTopic");
		MessageConsumer cu = s.createConsumer(myTopic);
		cu.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message arg0) {
				MapMessage message = (MapMessage) arg0;
				try {
					System.out.println("get message:" + message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while (true)
			;
	}

	@Test
	public void testTopicListener1() throws JMSException {
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic myTopic = s.createTopic("myTopic");
		MessageConsumer cu = s.createConsumer(myTopic);
		cu.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message arg0) {
				MapMessage message = (MapMessage) arg0;
				try {
					System.out.println("get message111:" + message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while (true)
			;
	}
}
