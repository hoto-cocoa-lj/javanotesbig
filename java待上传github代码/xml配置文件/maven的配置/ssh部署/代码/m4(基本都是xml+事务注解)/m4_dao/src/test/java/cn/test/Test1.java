package cn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.dao.CustomerDao;

public class Test1 {
@Test
public void test1(){
	String s="classpath:applicationContext-dao.xml";
	ApplicationContext ac=new ClassPathXmlApplicationContext(s);
	CustomerDao cd=(CustomerDao)ac.getBean("cd");
	System.out.println(cd);
	System.out.println(cd.findAll());
}
}
