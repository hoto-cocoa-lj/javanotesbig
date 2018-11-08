package cn.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.dao.CustomerDao;
import cn.itcast.service.CustomerService;


//两个类测试的结果一样，说明使用同一个ApplicationContext对象
//(ClassPathXmlApplicationContext/FileSystemXmlApplicationContext)
		

@ContextConfiguration(value = "classpath:applicationContext.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	@Autowired
	private CustomerService us;
	@Autowired
	private CustomerDao ud;

	@Test
	public void test1() {
		System.out.println(us); // @6d1ef78d
		System.out.println(ud); // @1a6c1270

	}

	@Test
	public void test2() {
		System.out.println(us); // @6d1ef78d
		System.out.println(ud); // @1a6c1270

	}
}
