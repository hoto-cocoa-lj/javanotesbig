package cn.itcast.jdbctempalte;

import java.beans.PropertyVetoException;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Demo
{
	@Test // 硬编码的方式
	public void test1() throws Exception
	{
		// c3p0
		ComboPooledDataSource ds = new ComboPooledDataSource();  //ioc
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/spring04");
		ds.setUser("root");									// di
		ds.setPassword("1234");
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();   
		jdbcTemplate.setDataSource(ds);					
		String sql="insert into account values(?,?)";
		jdbcTemplate.update(sql, "jack",1000);
		
	}
	
	@Test // ioc+di
	public void test2()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate =(JdbcTemplate)context.getBean("jdbcTemplate");
		String sql="insert into account values(?,?)";
		jdbcTemplate.update(sql, "rose",1000);
	}
}
