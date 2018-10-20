package com.domain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class Testa {

	@Test
	public void test() {
		User u = new UserImpl();
		u.t1();
	}

	@Test
	public void test1() {
		User u = new Big(new UserImpl());
		u.t1();
		u.t2();
		u.t3();
	}

	// 动态代理增强方法
	@Test
	public void test2() {
		User u1 = new UserImpl();
		User u = (User) Proxy.newProxyInstance(u1.getClass().getClassLoader(), u1.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ("t1".equals(method.getName())) {
							System.out.println("BIG--->增");
						}
						method.invoke(u1, args);
						return null;
					}
				});
		u.t1();
		u.t4();
	}

	// 动态代理增强方法,由于UserImpl2没有实现接口，这个方法会报错
	@Test
	public void test3() {
		UserImpl2 u1 = new UserImpl2();
		UserImpl2 u = (UserImpl2) Proxy.newProxyInstance(u1.getClass().getClassLoader(), u1.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ("t1".equals(method.getName())) {
							System.out.println("BIG--->增");
						}
						method.invoke(u1, args);
						return null;
					}
				});
		u.t1();
		u.t4();
	}

	// cglib的方式增强方法
	@Test
	public void test4() {
		UserImpl2 u1 = new UserImpl2();
		UserImpl2 u2 = (UserImpl2) Enhancer.create(UserImpl2.class, new MethodInterceptor() {

			@Override
			public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
				if ("t1".equals(arg1.getName())) {
					System.out.println("BIG--->增");
				}
				arg1.invoke(u1, arg2);
				return null;
			}
		});
		u2.t1();
		u2.t2();
	}
}

// 用装饰着模式增强方法
class Big implements User {
	private UserImpl u;

	@Override
	public void t1() {
		System.out.println("BIG--->增");
		u.t1();
	}

	@Override
	public void t2() {
		u.t2();
	}

	@Override
	public void t3() {
		u.t3();
	}

	@Override
	public void t4() {
		u.t4();
	}

	public Big(UserImpl u) {
		super();
		this.u = u;
	}

}