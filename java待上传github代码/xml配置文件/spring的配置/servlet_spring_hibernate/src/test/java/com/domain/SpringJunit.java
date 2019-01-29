package com.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.me.dao.UserDao;
import cn.me.service.UserService;
import cn.me.web.S1;

@ContextConfiguration(value = "classpath:applicationContext.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	@Autowired
	private UserService us;
	@Autowired
	private UserDao ud;
	@Autowired
	private S1 s;

	@Test
	public void test1() {
		System.out.println(us + "\t" + ud + "\t" + s);

	}

}
