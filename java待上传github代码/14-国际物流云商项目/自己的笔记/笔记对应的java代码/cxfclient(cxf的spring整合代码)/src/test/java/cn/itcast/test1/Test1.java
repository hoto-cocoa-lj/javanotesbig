package cn.itcast.test1;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.service.Car;
import cn.itcast.service.IUserService;
import cn.itcast.service.User;

public class Test1 {
public static void main(String[] args) {
	ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("applicationContext-ws-client.xml");
	IUserService s = (IUserService) c.getBean("myClient");
	String w = s.sayHi("fuck");
	System.out.println(w);
	User u=new User();
	u.setUsername("宋江");
	List<Car> l = s.findCarsByUser(u);
	for(Car cc:l){
		System.out.println(cc.getCarName());
	}
	
}
}
