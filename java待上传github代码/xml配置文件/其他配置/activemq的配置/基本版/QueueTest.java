package cn.me;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;

public class QueueTest {
	public static void main(String[] args) throws JMSException {
		ActiveMQConnectionFactory a = new ActiveMQConnectionFactory();
		Connection c = a.createConnection();
		c.start();
		// 第1个参数表示是否使用事务，如果不使用事务下面就不需要commit
		// 第2个参数表示应答模式,一般选第一个自动应答，第二个是手动，后两个不知道
		Session s = c.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue myQueue = s.createQueue("myQueue");
		MessageProducer producer = s.createProducer(myQueue);
		//使用TextMessage进行测试
		for (int i = 0; i < 2; i++) {
			TextMessage message = s.createTextMessage("hello queue" + i);
			producer.send(message);
		}
		s.commit();
		s.close();
		c.close();
	}

	@Test
	// 不commit的话，可以发现打印了信息，但是信息依旧在queue里
	// 注释掉两个close方法和加上死循环，可以在web端发现消费者
	// 不知道对方发多少信息不适用手动用监听
	public void testQueueReceiver() throws JMSException {
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue myQueue = s.createQueue("myQueue");
		MessageConsumer cu = s.createConsumer(myQueue);
		TextMessage message = (TextMessage) cu.receive();
		System.out.println(message.getText());
		s.commit();
		/*
		 * s.close(); c.close();
		 */
		while (true)
			;
	}

	@Test
	// 使用监听器，由于是queue消息模式，监听器启动前启动后的信息都能接收到
	public void testQueueListener() throws JMSException {
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue myQueue = s.createQueue("myQueue");
		MessageConsumer cu = s.createConsumer(myQueue);
		cu.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message arg0) {
				TextMessage message = (TextMessage) arg0;
				try {
					System.out.println("get message:" + message.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		while (true)
			;
	}
}
