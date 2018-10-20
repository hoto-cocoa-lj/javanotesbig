package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.service.AccountService;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJunit 
{
	@Autowired
	private AccountService accountService;
	
	
	@Test
	public void test()
	{
		//accountService.save(); //增
		//accountService.delete(); //删
		//accountService.update(); // 改
		accountService.findAll(); //全查--list
		//accountService.findByname();//查单个对象
		//accountService.findCount(); //查总个数
	}
}
