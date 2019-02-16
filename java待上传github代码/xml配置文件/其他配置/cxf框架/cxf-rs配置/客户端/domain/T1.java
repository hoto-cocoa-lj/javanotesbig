package cn.itcast.domain;

import java.util.Collection;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

public class T1 {
	@Test
	public void t1() {
		String url = "http://localhost:8000/xiangmuming/ws/userService/user";
		WebClient c = WebClient.create(url);
		User11 u = new User11();
		u.setUsername("name1");
		u.setCity("beijing");
		// c.post(u);
		// c.put(u);
		Collection<? extends User11> x = c.getCollection(User11.class);
		System.out.println(x);
	}

	@Test
	public void t2() {
		String url = "http://localhost:8000/xiangmuming/ws/userService/user";
		WebClient c = WebClient.create(url);
		c.get();
	}
}
