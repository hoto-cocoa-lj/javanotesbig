package com.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages = "com")
@PropertySource(value = "classpath:jdbc.properties")
public class SpringConfig {

	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean(name = "c3p0")
	public DataSource createDataSourceC3p0() throws PropertyVetoException {
		System.out.println(this);
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(driver);
		ds.setJdbcUrl(url);
		ds.setUser(username);
		ds.setPassword(password);
		return ds;
	}

	@Bean // 4.3以前要给spring配置一个解析器来解析${}
	public static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
