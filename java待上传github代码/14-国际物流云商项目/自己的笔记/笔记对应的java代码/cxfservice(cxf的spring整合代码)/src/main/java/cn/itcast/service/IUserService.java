package cn.itcast.service;

import java.util.List;

import javax.jws.WebService;

import cn.itcast.domain.Car;
import cn.itcast.domain.User;

@WebService
public interface IUserService {
	
	public String sayHi(String name);
	public List<Car> findCarsByUser(User user);
	
}
