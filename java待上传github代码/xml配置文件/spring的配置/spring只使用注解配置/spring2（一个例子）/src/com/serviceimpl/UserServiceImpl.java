package com.serviceimpl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Value("要开始炒作service了")
	private String name;
	@Autowired
	private UserDao ud;

	@Override
	public void save() {
		System.out.println(name);
		// String x = "applicationContext.xml";
		// ClassPathXmlApplicationContext c = new
		// ClassPathXmlApplicationContext(x);
		// System.out.println("impl" + c);
		// UserDao ud = (UserDao) c.getBean("UserDaoImpl");
		ud.save();
	}

	@PostConstruct
	private void start() {
		// TODO Auto-generated method stub
		System.out.println("servicec start");
	}

	@PreDestroy
	private void end() {
		// TODO Auto-generated method stub
		System.out.println("servicec end");
	}

}
