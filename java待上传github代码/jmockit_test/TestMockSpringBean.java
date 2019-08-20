package jmockit_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;

@ContextConfiguration(value = "classpath:applicationContext.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class TestMockSpringBean {
	@Autowired
	A a;

	@Test
	public void testSpringBeanMockByExpectations() {
		new Expectations(A.class) {
			{
				a.a2();
				result = "ByExpectations";
			}
		};
		System.out.println(a.a2());
		System.out.println(a.a1(1));
	}

	@Test
	public void testSpringBeanMockByMockUp() {
		new MockUp<A>(A.class) {
			@Mock
			String a2() {
				return "ByMockUp";
			}
		};
		System.out.println(a.a2());
		System.out.println(a.a1(1));
	}
}
