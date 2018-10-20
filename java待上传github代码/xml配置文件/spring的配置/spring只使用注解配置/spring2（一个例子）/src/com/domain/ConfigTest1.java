package com.domain;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.SpringConfig;
import com.serviceimpl.UserServiceImpl;

public class ConfigTest1 {
	@Test
	public void test() throws SQLException {
		ApplicationContext a = new AnnotationConfigApplicationContext(SpringConfig.class);
		UserServiceImpl b = (UserServiceImpl) a.getBean("userServiceImpl");
		DataSource ds = (DataSource) a.getBean("c3p0");
		System.out.println(ds);
		System.out.println(ds.getConnection());
		b.save();
	}
}
