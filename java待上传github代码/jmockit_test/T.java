package jmockit_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

public class T {
	@Mocked
	A a;

	@Test
	public void t1() {
		new Expectations() {
			{
				a.a1(1);
				result = "fuck";
			}
		};

		assertThat(a.a1(1), is("fuck"));
		new Verifications() {
			{
				a.a1(1);
				times = 1;
			}
		};
	}

	@Test
	public void t2(@Mocked final A a) {
		new Expectations() {
			{
				a.a1(1);
				result = "fuck";
			}
		};

		assertThat(a.a1(1), is("fuck"));
		new Verifications() {
			{
				a.a1(1);
				times = 1;
			}
		};
	}
}
