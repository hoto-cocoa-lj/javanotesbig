package cn.itcast.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component(value="topicConsumer2")
public class TopicListener2 implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		TextMessage m=(TextMessage)arg0;
		try {
			System.out.println(m.getText()+"topic消费者2");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
