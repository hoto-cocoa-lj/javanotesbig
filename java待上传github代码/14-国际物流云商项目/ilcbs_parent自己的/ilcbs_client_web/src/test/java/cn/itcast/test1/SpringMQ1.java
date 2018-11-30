package cn.itcast.test1;

import javax.jms.JMSException;
import javax.jms.MapMessage;
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

@ContextConfiguration(value = "classpath:applicationContext-mq.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringMQ1 {
	@Autowired
	@Qualifier(value="jmsQueueTemplate")
	private JmsTemplate jq;
	@Autowired
	@Qualifier(value="jmsTopicTemplate")
	private JmsTemplate jt;
	@Test
	public void sendVerCode(){
		MessageCreator m=new MessageCreator() {			
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage m1= session.createMapMessage();
				m1.setString("telephone", "1333333");
				return m1;
			}
		};
		jq.send("ilcbs_client",m);
	}

}
