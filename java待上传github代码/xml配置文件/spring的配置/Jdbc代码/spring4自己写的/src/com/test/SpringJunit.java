package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.AccountService;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	@Autowired
	private AccountService as1;

	@Test
	public void test1() {
		as1.t4();
	}
}
