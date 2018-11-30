package cn.itcast.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.itcast.utils.RandomCode;

@Component(value="queue1")
public class QueueListener implements MessageListener{
	@Autowired
	private RedisTemplate<String,String> rt;
	@Override
	public void onMessage(Message arg0) {
		MapMessage m=(MapMessage)arg0;
		try {
//			String i=new Integer(RandomCode.genCode()).toString();
			System.out.println("here1");
			String i=RandomCode.genCode()+"";
			String t=m.getString("telephone");
			System.out.println("here2,t="+t+"\ti="+i);
			rt.opsForValue().set(t,i);
			System.out.println("here3");
			System.out.println(t+":"+i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
