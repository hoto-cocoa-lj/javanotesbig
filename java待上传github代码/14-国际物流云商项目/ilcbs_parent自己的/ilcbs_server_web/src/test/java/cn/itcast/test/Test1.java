package cn.itcast.test;

import java.util.Set;

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

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test1 {
	String url="192.168.153.129";
	@Test
	public void t1(){
		Jedis j = new Jedis(url,6379);
		String s=j.get("modules");
		System.out.println(s);
	}
	@Test
	public void t2(){
		JedisPoolConfig c = new JedisPoolConfig();
		c.setMaxIdle(10);
		c.setMaxTotal(30);
		JedisPool jp = new JedisPool(c,url);
		Jedis j = jp.getResource();
		Set<String> k = j.keys("*");
		for(String a:k){
		System.out.println(a+":"+j.get(a));
		}
	}
	@Test
	public void t3() throws JMSException{
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Queue q = s.createQueue("itcast297_queue");
		MessageProducer p = s.createProducer(q);
		for(int i=0;i<9;i++){
			TextMessage m = s.createTextMessage("fuck you---"+i);
			p.send(m);
		}
		s.commit();
		s.close();
		c.close();		
	}
	@Test
	public void t4() throws JMSException{
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		//下面如果设置true，由于不提交所以信息一直显示在队列里。
		//设置了false虽然没提交但其实会自己提交，所以信息会出列。
		//然后因为没跳出while不会关闭链接，所以这个消费者一直挂着
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue q = s.createQueue("itcast297_queue");
		MessageConsumer cc = s.createConsumer(q);
		while(true){
			TextMessage m = (TextMessage) cc.receive();
			//这里永远不会break
			if(m==null || m.getText()==null||m.getText().length()==0){
				break;
			}
			String text = m.getText();
			System.out.println(text);
		}
		System.out.println("close");
		s.commit();
		s.close();
		c.close();
	}
	@Test
	public void t5() throws JMSException{
		ActiveMQConnectionFactory f = new ActiveMQConnectionFactory();
		Connection c = f.createConnection();
		c.start();
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue q = s.createQueue("itcast297_queue");
		MessageConsumer cc = s.createConsumer(q);
		cc.setMessageListener(new MessageListener() {			
			@Override
			public void onMessage(Message arg0) {
				TextMessage m=(TextMessage)arg0;
				try {
					System.out.println(m.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		while(true);	
	}
}
