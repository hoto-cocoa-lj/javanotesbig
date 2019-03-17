package s;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueController {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@RequestMapping("/s.d")
	public void send(String text) {
		jmsMessagingTemplate.convertAndSend("itcast", text);
	}

	@RequestMapping("/s1.d")
	public void sendMap(String z) {
		Map map = new HashMap();
		map.put("mobile", "13900001111");
		map.put("content", "恭喜获得10元代金券");
		map.put("z", z);
		jmsMessagingTemplate.convertAndSend("itcast_map", map);
	}
}