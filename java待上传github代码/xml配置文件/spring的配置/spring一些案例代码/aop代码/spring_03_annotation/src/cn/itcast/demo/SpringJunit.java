package cn.itcast.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.domain.Person;
import cn.itcast.springconfig.SpringConfig;

//@ContextConfiguration("classpath:applicationContext.xml") // 加载配置文件
@ContextConfiguration(classes=SpringConfig.class) //加载注解类
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJunit 
{
	@Autowired
	private Person person;
	
	@Test
	public void test()
	{
		person.save();
		
	}
}
