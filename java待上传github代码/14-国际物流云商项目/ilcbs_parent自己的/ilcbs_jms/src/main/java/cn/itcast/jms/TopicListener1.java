package cn.itcast.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component(value="topicConsumer1")
public class TopicListener1 implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		TextMessage m=(TextMessage)arg0;
		try {
			System.out.println(m.getText()+"topic消费者1");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
