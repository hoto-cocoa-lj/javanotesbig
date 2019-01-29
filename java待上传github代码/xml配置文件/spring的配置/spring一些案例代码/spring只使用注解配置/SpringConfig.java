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
//这里用来代替applicationContext.xml的<context:component-scan base-package="com"></context:component-scan>
@PropertySource(value = "classpath:jdbc.properties")
//这里用来读取src下的jdbc.properties的（c3p0的）配置数据
public class SpringConfig {
	
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;

	@Override 					
	public String toString() {					//检查用，可以不写。使用注解可以不写set方法
		return "SpringConfig [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ "]";
	}

	@Bean(name = "c3p0")
	//这里用来代替applicationContext.xml的c3p0的配置，包括c3p0的参数和参数的导入
	//<context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
	public DataSource createDataSourceC3p0() throws PropertyVetoException {
		System.out.println(this);
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(driver);
		ds.setJdbcUrl(url);
		ds.setUser(username);
		ds.setPassword(password);
		return ds;
	}

	// 4.3以前要给spring配置一个解析器来解析${}
	@Bean
	public static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
	





