package cn.itcast.springconfig;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages="cn.itcast")
@EnableTransactionManagement  //<tx:annotation-driven transaction-manager="transactionManager"/>
public class SpringConfig 
{
	// 创建出来c3p0 给spring
	@Bean(name="c3p0")
	public  DataSource createDataSourceC3p0() throws PropertyVetoException
	{
		ComboPooledDataSource ds = new ComboPooledDataSource();  //ioc
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/spring04");
		ds.setUser("root");									// di
		ds.setPassword("1234");
		return ds;
	}
	
	@Bean(name="jdbcTemplate")
	public  JdbcTemplate createJdbcTemplate(@Qualifier("c3p0") DataSource ds)  // 使用注解问spring要
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(); 
		jdbcTemplate.setDataSource(ds);
		return jdbcTemplate;
	}
	
	
	@Bean(name="transactionManager")
	public  DataSourceTransactionManager createDataSourceTransactionManager(@Qualifier("c3p0") DataSource ds)  // 使用注解问spring要
	{
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(ds);
		return dataSourceTransactionManager;
	}
}
