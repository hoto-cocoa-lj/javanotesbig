package com.domain;

public class User1 {
	private User u;
	private String n;
	private String p;

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "User1 [u=" + u + ", n=" + n + ", p=" + p + "]";
	}

}
