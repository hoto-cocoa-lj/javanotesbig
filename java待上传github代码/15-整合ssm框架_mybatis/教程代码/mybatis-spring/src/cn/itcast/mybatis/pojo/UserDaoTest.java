package cn.itcast.mybatis.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.mybatis.dao.UserDao;
import cn.itcast.mybatis.dao.impl.UserDaoImpl;

public class UserDaoTest {

	public static void main(String[] args) {
//		1、加载spring容器|初始化容器
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		2、从容器中获取对象
//		app.getBean("userDao");
		UserDao userDao = app.getBean(UserDao.class);
		User user = userDao.findById(30);
		System.out.println(user);
	}

}
