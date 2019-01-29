package com.domain;

public class T {
	private Integer tid, price;
	private String tname;

	@Override
	public String toString() {
		return "T [tid=" + tid + ", price=" + price + ", tname=" + tname + "]";
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

}
