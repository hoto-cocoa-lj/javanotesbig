package jmockit_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import mockit.Deencapsulation;
import mockit.Expectations;

public class TestOneMethod {

	// 对A这个类有效，所以new出来的对象的a1(1)被mock，但是a1(2)没被mock，且a2也没被mock
	/*
	 * 用Deencapsulation mock了私有方法private_method，也可以用MockUp mock私有方法
	 * 这两个东西mock私有方法，都是该类其他public方法调用了该私有方法，mock私有方法后，
	 * 调用public可以发现私有方法被mock了，不能直接在外边调用私有方法。
	 */

	@Test
	public void testMockOneMethodOfClass() {
		final A a = new A();
		new Expectations(A.class) {
			{
				a.a1(1);
				result = "testMockOneMethodOfClass";

				Deencapsulation.invoke(a, "private_method");
				result = "pm";

			}
		};
		A a1 = new A();
		A a2 = new A();
		// assertThat(a1.a1(2), is("testMockOneMethodOfClass"));
		assertThat(a1.a1(1), is("testMockOneMethodOfClass"));
		// assertThat(a2.a1(1), is("testMockOneMethodOfClass"));

		assertThat(a1.public_method(), is("pm"));
		assertThat(a1.a2(), is("a2 static"));

	}

	// 只对a.a1(1)有影响
	@Test
	public void testMockOneMethodOfInstance() {
		final A a = new A();
		new Expectations(a) {
			{
				a.a1(1);
				result = "testMockOneMethodOfClass";
			}
		};
		A a1 = new A();
		A a2 = new A();
		assertThat(a1.a1(1), is("Hello，0000"));
		assertThat(a.a1(1), is("testMockOneMethodOfClass"));
	}
}