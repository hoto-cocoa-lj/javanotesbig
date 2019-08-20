package jmockit_test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.Mocked;

//@Mocked注解用途
public class TestMockedInjectableTested {
	/**
	 * 加上了JMockit的API @Mocked, JMockit会帮我们实例化这个对象，不用担心它为null mocked给整个class
	 * mock，包括静态和非静态方法，且自己new的对象也mock Injectable给单个对象的非静态方法mock，其他一概不管
	 * Tested相当于自己new的对象，完全不mock 搭配Injectable使用，主对象用Tested，子对象用Injectable
	 */
	/*
	 * Tested和搭配mocked使用无效，这里testA2_1(@Mocked B b)能使用且B.you()返回0，
	 * 原因是testA2_1找到了@injectable的B，给a.setB，然后@Mocked B，修改了you的返回值。
	 * 如果去掉@injectable的B直接使用@Mocked B b相当于new A()，属性全是null，
	 * 会在assertThat(a.getB(), org.hamcrest.CoreMatchers.notNullValue())报错，
	 * 不过可以看到b依旧被mock了。
	 * 
	 * 使用@Mocked标记A a后，如果不写new Expectations(A.class)，那么所有方法默认返回对象的返回null，
	 * 返回基础类型的返回默认值。如果写了new Expectations(A.class)，那么只要该方法没有被重写， 则依旧返回原值。
	 */
	// 在类属性上 @Injectable B b和 方法参数里testA2_(@Injectable B b) 似乎一样

	// @Tested
	@Mocked
	// @Injectable
	A a;
	@Mocked
	A_Interface ai;
	@Mocked
	A_Abstract aba;

	// 不能对一个类定义多次，不然似乎会覆盖
	// A aa;
	@Injectable
	B b;

	@Injectable
	C c;

	// 当@Mocked作用于class
	@Test
	public void testA1() {
		/*
		 * new Expectations(A.class) { { a.a2(); result = "3"; } };
		 */
		// 静态方法被mock,返回了null
		assertThat(a.a1(1), nullValue());
		// 非静态方法被mock
		assertThat(a.a2(), nullValue());
		// 自已new一个，也同样如此，方法都被mock了
		assertThat(a.getB(), nullValue());

		A aa = new A();
		assertThat(aa.a1(1), nullValue());
		assertThat(aa.a2(), nullValue());
	}

	// 当@Tested作用于class,这里b必须用@Injectable,不能用@Mocked,否则b是null
	@Test
	public void testA2() {
		// 非静态方法没被mock
		assertThat(a.a1(1), is("Hello，0000"));
		// 静态方法没被mock
		assertThat(a.a2(), is("a2 static"));
		// b是@Injectable，所以非静态方法被mock
		assertThat(a.getB(), org.hamcrest.CoreMatchers.notNullValue());
		assertThat(a.getB().fuck(), nullValue());
		assertThat(a.getB().you(), is(11));
		assertThat(a.getB().getC(), nullValue());

		// 自已new一个，没被mock
		A aaa = new A();
		assertThat(aaa.a1(0), is("你好，11111"));
		assertThat(aaa.a2(), is("a2 static"));
	}

	// 当@Tested作用于class,搭配@Mocked使用（并非实际搭配@Mocked）
	@Test
	public void testA2_1(@Mocked B b) {
		assertThat(b.fuck(), nullValue());
		// 非静态方法没被mock
		assertThat(a.a1(1), is("Hello，0000"));
		// 静态方法没被mock
		assertThat(a.a2(), is("a2 static"));

		assertThat(a.getB(), org.hamcrest.CoreMatchers.notNullValue());
		assertThat(a.getB().fuck(), nullValue());
		assertThat(a.getB().you(), is(0));
		assertThat(a.getB().getC(), nullValue());

		// 自已new一个，没被mock
		A aaa = new A();
		assertThat(aaa.a1(0), is("你好，11111"));
		assertThat(aaa.a2(), is("a2 static"));
	}

	// 当@Tested作用于class,搭配@Injectable
	@Test
	public void testA2_2(@Injectable B b) {
		// 非静态方法没被mock
		assertThat(a.a1(1), is("Hello，0000"));
		// 静态方法没被mock
		assertThat(a.a2(), is("a2 static"));

		assertThat(a.getB(), org.hamcrest.CoreMatchers.notNullValue());
		assertThat(a.getB().fuck(), nullValue());
		assertThat(a.getB().you(), is(11));
		assertThat(a.getB().getC(), nullValue());

		// 自已new一个，没被mock
		A aaa = new A();
		assertThat(aaa.a1(0), is("你好，11111"));
		assertThat(aaa.a2(), is("a2 static"));
	}

	// 当@Tested作用于class,搭配@Tested,注入失败
	@Test
	public void testA2_3() {
		// 非静态方法没被mock
		assertThat(a.a1(1), is("Hello，0000"));
		// 静态方法没被mock
		assertThat(a.a2(), is("a2 static"));

		assertThat(a.getB(), org.hamcrest.CoreMatchers.nullValue());
	}

	@Test // 测试@Injectable
	public void testA3() {
		// 非静态方法被mock,返回了null
		assertThat(a.a1(1), nullValue());
		// 静态方法没被mock
		assertThat(a.a2(), is("a2 static"));
		assertThat(a.getB(), nullValue());

		// 自已new一个，没被mock
		A aaa = new A();
		assertThat(aaa.a1(0), is("你好，11111"));
		assertThat(aaa.a2(), is("a2 static"));
	}

	// 当@Mocked作用于interface
	@Test
	public void testAIAndAb() {
		assertThat(ai.ai1(), nullValue());
		assertThat(aba.aba1(), nullValue()); // 报错
	}

	// 加上了JMockit的API @Mocked, JMockit会帮我们实例化这个对象，尽管这个对象的类型是一个接口，不用担心它为null
	@Mocked
	HttpSession session;

	// 当@Mocked作用于interface
	@Test
	public void testMockedInterface() {
		// （返回类型为String）也不起作用了，返回了null
		Assert.assertTrue(session.getId() == null);
		// （返回类型为原始类型）也不起作用了，返回了0
		Assert.assertTrue(session.getCreationTime() == 0L);
		// (返回类型为原非始类型，非String，返回的对象不为空，这个对象也是JMockit帮你实例化的，同样这个实例化的对象也是一个Mocked对象)
		// Assert.assertTrue(session.getServletContext() != null);
		// Mocked对象返回的Mocked对象，（返回类型为String）的方法也不起作用了，返回了null
		Assert.assertTrue(session.getServletContext().getContextPath() == null);
	}

}