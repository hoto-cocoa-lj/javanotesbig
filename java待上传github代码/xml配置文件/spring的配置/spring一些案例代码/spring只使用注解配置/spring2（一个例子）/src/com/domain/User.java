package com.domain;

public class User {
	private String name;
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void a() {
		System.out.println("user start");
	}

	public void b() {
		System.out.println("user byebye");
	}

	public void run() {
		System.out.println(name + price + "run--user-");
	}

	public User(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", price=" + price + "]";
	}

	// public User() {
	// super();
	// System.out.println(name + price + " am user");
	// }

}
