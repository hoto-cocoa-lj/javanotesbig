package cn.itcast.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component(value="queue1")
public class QueueListener implements MessageListener{

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

}
