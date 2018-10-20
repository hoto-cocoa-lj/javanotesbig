package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.service.TranFerService;
import cn.itcast.springconfig.SpringConfig;

//@ContextConfiguration("classpath:applicationContext.xml")
@ContextConfiguration(classes=SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJunit 
{
	@Autowired
	private TranFerService tranFerService;
	@Test
	public void test()
	{
		tranFerService.tranfer("jack", "rose", 500);
	}
}
