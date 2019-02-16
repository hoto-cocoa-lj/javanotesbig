package cn.itcast.domain;

import javax.xml.bind.annotation.XmlRootElement;

//重要的是@XmlRootElement(name = "Car")和成员属性必须和server一样
@XmlRootElement(name = "Car")
public class Car11 {
	private Integer id;
	private String carName;
	private Double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", carName=" + carName + ", price=" + price + "]";
	}

}
