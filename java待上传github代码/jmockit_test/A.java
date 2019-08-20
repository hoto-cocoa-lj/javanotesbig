package jmockit_test;

import org.springframework.stereotype.Service;

/** 一个简单的类，能用不同语言打招呼 */

@Service
public class A {
	String a1;
	B b;

	private String private_method() {
		return "this is a private_method";
	}

	public String public_method() {
		return private_method();
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	// 向JMockit打招呼
	public String a1(int i) {
		if (i == 0) {
			// 在中国，就说中文
			return "你好，11111";
		} else {
			// 在其它国家，就说英文
			return "Hello，0000";
		}
	}

	public static String a2() {
		return "a2 static";
	}

}