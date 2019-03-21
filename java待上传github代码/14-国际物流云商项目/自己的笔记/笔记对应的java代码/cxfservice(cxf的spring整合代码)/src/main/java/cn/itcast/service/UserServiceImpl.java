package cn.itcast.service;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.domain.Car;
import cn.itcast.domain.User;

public class UserServiceImpl implements IUserService {

	public String sayHi(String name) {
		System.out.println("hi,"+name);
		return "hi,"+name;
	}

	public List<Car> findCarsByUser(User user) {
		if ("宋江".equals(user.getUsername())) {
			List<Car> cars = new ArrayList<Car>();
			Car car1 = new Car();
			car1.setId(1);
			car1.setCarName("大众途观");
			car1.setPrice(200000d);
			cars.add(car1);

			Car car2 = new Car();
			car2.setId(2);
			car2.setCarName("现代ix35");
			car2.setPrice(170000d);
			cars.add(car2);

			return cars;
		} else {
			return null;
		}
	}

}
