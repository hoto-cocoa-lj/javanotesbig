package com.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//两个类测试的结果一样，说明使用同一个ApplicationContext对象
//(ClassPathXmlApplicationContext/FileSystemXmlApplicationContext)
		
import com.dao.UserDao;
import com.service.UserService;

@ContextConfiguration(value = "classpath:applicationContext.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	@Autowired
	private UserService us;
	@Autowired
	private UserDao ud;

	@Test
	public void test1() {
		System.out.println(us); // @6d1ef78d
		System.out.println(ud); // @1a6c1270
		us.save();
	}

	@Test
	public void test2() {
		System.out.println(us); // @6d1ef78d
		System.out.println(ud); // @1a6c1270
		ud.save();
	}
}
