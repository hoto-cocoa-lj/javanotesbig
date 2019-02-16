package cn.me;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(value = "classpath:applicationContext-mq.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate j1;
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate j2;

	@Test
	public void test1() {
		j1.send("myQueue", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("test spring mq");
			}
		});
	}

	@Test
	public void test2() {
		for (int i = 0; i < 9; i++) {
			j2.send("myTopic", new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage("test spring mqtopic");
				}
			});
		}
	}

}
