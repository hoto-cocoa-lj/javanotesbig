package com.domain;

import org.junit.Test;

public class T {
	public void run() {
		System.out.println("FFFF");
	}

	@Test
	public void c() {
		T t = new X();
		t.run();
	}
}

class X extends T {
	@Override
	public void run() {
		System.out.println("XXXX");
	}

	public void o() {

	}
}
