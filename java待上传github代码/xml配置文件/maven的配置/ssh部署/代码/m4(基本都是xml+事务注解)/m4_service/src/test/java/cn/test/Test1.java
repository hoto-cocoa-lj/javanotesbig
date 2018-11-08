package cn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.service.CustomerService;

public class Test1 {
@Test
public void test1(){
	String s="classpath:applicationContext-service.xml";
	ApplicationContext ac=new ClassPathXmlApplicationContext(s);
	CustomerService cs=(CustomerService)ac.getBean("cs");
	System.out.println(cs);
	System.out.println(cs.findAll());
}
}
