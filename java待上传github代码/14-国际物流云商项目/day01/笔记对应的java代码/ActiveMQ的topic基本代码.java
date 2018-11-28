package cn.itcast.test;

import java.util.Set;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test2 {
	
	@Test			//topic生产者，类似queue的
	public void test1() throws JMSException{
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic l = s.createTopic("itcast297_topic");
		MessageProducer p = s.createProducer(l);
		TextMessage m1 = s.createTextMessage("fuck m1");
		for(int i=0;i<9;i++){			
			MapMessage m = s.createMapMessage();
			m.setString("user", "fuck");
			m.setString("pw", "you");
			p.send(m);		
			p.send(m1);
		}
		s.close();
		c.close();		
	}
	
	@Test			//topic消费者，使用receive手动消费，类似queue的
	public void test2() throws JMSException{
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic l = s.createTopic("itcast297_topic");
		MessageConsumer cc = s.createConsumer(l);
		
		cc.setMessageListener(new MessageListener() {			
			@Override
			public void onMessage(Message arg0) {
				try {
					MapMessage m=(MapMessage)arg0;
					System.out.println(m.getString("user"));
				} catch (Exception e) {
					System.out.println("error");
				}
				try {
					TextMessage m1=(TextMessage)arg0;
					System.out.println(m1.getText());
				} catch (Exception e) {
					System.out.println("error");
				}
			}
		});
		while(true);
	}
	@Test			//topic监听消费，类似queue的
	public void test3() throws JMSException{
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic l = s.createTopic("itcast297_topic");
		MessageConsumer cc = s.createConsumer(l);
		
		cc.setMessageListener(new MessageListener() {			
			@Override
			public void onMessage(Message arg0) {
				try {
					MapMessage m=(MapMessage)arg0;
					System.out.println(m.getString("test3 user"));
				} catch (Exception e) {
					System.out.println("error");
				}
				try {
					TextMessage m1=(TextMessage)arg0;
					System.out.println("test3\t"+m1.getText());
				} catch (Exception e) {
					System.out.println("error");
				}
			}
		});
		while(true);
	}
}
