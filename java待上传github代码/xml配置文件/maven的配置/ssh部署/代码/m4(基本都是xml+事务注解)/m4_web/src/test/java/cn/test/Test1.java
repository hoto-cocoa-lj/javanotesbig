package cn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.service.CustomerService;
import cn.itcast.web.CustomerAction;

public class Test1 {
@Test
public void test1(){
	String s="classpath:applicationContext-web.xml";
	ApplicationContext cx=new ClassPathXmlApplicationContext(s);
	CustomerAction ca=(CustomerAction)cx.getBean("ca");
	System.out.println(ca);
	System.out.println(ca.findAll());
}
}
