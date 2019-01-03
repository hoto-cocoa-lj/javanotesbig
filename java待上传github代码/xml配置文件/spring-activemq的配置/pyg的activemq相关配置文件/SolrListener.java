package com.pyg.search.listener;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;

/*
http://192.168.153.129:8161/可以查消费者生产者，账号密码都是admin。

这里接收到消息，我只进行打印了。发过来的消息是Long数组，
这里使用ActiveMQObjectMessage处理。使用ActiveMQMapMessage
可以处理Map消息，但是map的value不能是数组，能不能是其他类型还没测试。



*/
public class SolrListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {		
		ActiveMQObjectMessage m1=(ActiveMQObjectMessage)arg0;
		System.out.println("m1="+m1);
		try {
			Long[] p = (Long[]) m1.getObject();
			System.out.println(Arrays.toString(p));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			

		
	}
	
}
