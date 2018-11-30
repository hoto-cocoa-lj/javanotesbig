package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration(value = "classpath:applicationContext.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	@Autowired
	private RedisTemplate r;

	@Test
	public void test1() {
		Object o = r.opsForValue().get("modules");
		System.out.println(o);
		r.opsForValue().set("fuck","you");
		o = r.opsForValue().get("fuck");
		System.out.println(o);
		r.delete("fuck");
		o = r.opsForValue().get("fuck");
		System.out.println(o);
	}

}
