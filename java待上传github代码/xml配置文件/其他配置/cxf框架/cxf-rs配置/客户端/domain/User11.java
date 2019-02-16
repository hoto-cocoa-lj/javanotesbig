package cn.itcast.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

//重要的是@XmlRootElement(name = "Car")和成员属性必须和server一样
@XmlRootElement(name = "User")
public class User11 {
	private Integer id;
	private String username;
	private String city;

	private List<Car11> cars = new ArrayList<Car11>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Car11> getCars() {
		return cars;
	}

	public void setCars(List<Car11> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", city=" + city + ", cars=" + cars + "]";
	}

}
