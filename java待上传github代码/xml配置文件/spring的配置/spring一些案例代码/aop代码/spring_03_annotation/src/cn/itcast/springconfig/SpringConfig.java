package cn.itcast.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;

@Configuration // 当前类是注解类
@ComponentScan(basePackages="cn.itcast") //<context:component-scan base-package="cn.itcast"></context:component-scan>
@EnableAspectJAutoProxy   //<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
public class SpringConfig {
		
	// 目前没有别人的类
}
