package cc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cc.service.CustomerService;




@ContextConfiguration(value = "classpath:applicationContext-service.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {

	@Autowired
	private CustomerService cs;

	@Test
	public void test1() {
		System.out.println(cs); // @6d1ef78d
cs.a();
	}


}
