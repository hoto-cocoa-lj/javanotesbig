package com.domain;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.service.UserService;

public class Test1 {

	@Test
	public void test1() {
		String x = "applicationContext.xml";
		String x1 = "/src/applicationContext.xml";
		// ClassPathXmlApplicationContext c = new
		// ClassPathXmlApplicationContext(x);
		FileSystemXmlApplicationContext c = new FileSystemXmlApplicationContext(x1);
		// User u = (User) c.getBean("User");
		// u.run();
		// User1 u1 = (User1) c.getBean("User1");
		// u1.getU().setPrice(250.0);
		C c1 = (C) c.getBean("C1");
		System.out.println(c1);
		c.close();
	}

	@Test
	public void test2() {

		String x = "applicationContext.xml";
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(x);
		System.out.println("test" + c);
		// User u = (User) c.getBean("User");
		UserService us = (UserService) c.getBean("userServiceImpl");
		us.save();
		c.close();
	}

	@Test
	public void test3() throws SQLException {

		String x = "applicationContext.xml";
		ClassPathXmlApplicationContext c1 = new ClassPathXmlApplicationContext(x);
		ComboPooledDataSource ds = (ComboPooledDataSource) c1.getBean("c3p0");
		Connection c = ds.getConnection();
		System.out.println(ds);
		System.out.println(c);

	}

	@Test
	public void test4() throws SQLException {
		String x = "applicationContext.xml";
		ClassPathXmlApplicationContext c1 = new ClassPathXmlApplicationContext(x);

	}
}
