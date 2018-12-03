package cn.itcast.mybatis.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.mybatis.dao.UserDao;
import cn.itcast.mybatis.dao.impl.UserDaoImpl;
import cn.itcast.mybatis.mapper.UserMapper;

public class UserMapperTest {

	public static void main(String[] args) {
//		1、加载spring容器|初始化容器
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		2、从容器中获取对象
//		app.getBean("userDao");
		UserMapper mapper = app.getBean(UserMapper.class);
		User user = mapper.findById(30);
		System.out.println(user);
	}

}
