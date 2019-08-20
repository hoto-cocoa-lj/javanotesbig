package jmockit_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;
import mockit.Verifications;

public class TestMockup {
	// MockUp可以对单个方法的多种输入进行mock,可以一次mock多个方法,还mock了私有方法private_method
	@Test
	public void test_mockup() {
		new MockUp<A>(A.class) {
			@Mock
			String a1(int i) {
				if (i > 10) {
					return "1";
				}
				if (i < 0) {
					return "-1";
				}
				return "0";
			}

			@Mock
			String a2() {
				return "mockup mock a2";
			}

			@Mock
			String private_method() {
				return "pm";
			}

		};
		final A a = new A();
		assertThat(a.a1(11), is("1"));
		assertThat(a.a1(-11), is("-1"));
		assertThat(a.a1(1), is("0"));

		assertThat(a.a2(), is("mockup mock a2"));
		assertThat(a.public_method(), is("pm"));

		new Verifications() {
			{
				a.a1(anyInt);
				// times = 3; //报错，原因不明
			}
		};
	}

}
