package cn.me;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(value = "classpath:applicationContext-mq-consumer.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit2 {

	@Test
	public void test1() {
		while (true)
			;
	}

}
