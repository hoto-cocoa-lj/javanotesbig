package jmockit_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import mockit.Capturing;
import mockit.Expectations;

//什么测试场景用@Capturing 
//我们只知道父类或接口时，但我们需要控制它所有子类的行为时，子类可能有多个实现（可能有人工写的，
//也可能是AOP代理自动生成时）。就用@Capturing。 
//new Expectations可以写多个
public class TestCapturing {
	A_Interface aii = new A_Interface() {
		public String ai1() {
			return "ai1";
		}
	};

	// @Capturing //也可以这么写
	// A_Interface ai;

	@Test
	public void testWithCapturing(@Capturing final A_Interface ai) {
		new Expectations() {
			{
				ai.ai1();
				result = "00";
			}
		};
		new Expectations() {
			{
				ai.ai1();
				result = "000";
			}
		};
		assertThat(aii.ai1(), is("000"));
	}

	@Test
	public void testWithoutCapturing() {
		assertThat(aii.ai1(), is("000")); // return ai1
	}
}