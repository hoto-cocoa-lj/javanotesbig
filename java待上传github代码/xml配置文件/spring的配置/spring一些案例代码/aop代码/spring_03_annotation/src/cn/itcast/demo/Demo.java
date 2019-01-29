package cn.itcast.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import cn.itcast.domain.Person;
import cn.itcast.domain.User;
import cn.itcast.domain.UserImpl;

public class Demo 
{
	@Test
	public void test()
	{
		User user = new UserImpl();
		user.save();
		
		// 想要对 UserImpl里面的save方法进行增强
		// 目标类: UserImpl
		// 增强方法:save()
	}
	
	@Test
	public void test2()
	{
		// 目标类
		final UserImpl user=new UserImpl();
		
		
		// jdk的动态代理
		// 参数一: 和目标类一样的类加载器
		// 参数二: 和目标类一样的接口 
		// 参数三: 增强的业务
		User userproxy=(User)Proxy.newProxyInstance(
				user.getClass().getClassLoader(), 
				user.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override //增强的业务
					// 参数一: 固定值
					// 参数二: 要增强的方法 (原有的方法)
					// 参数三: 方法运行时候需要的参数
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						if("save".equals(method.getName()))
						{
							// 让原有方法执行
							// 本身应该调用这个方法的对象
							System.out.println("之前增强了...");
							method.invoke(user, args);
							System.out.println("之后增强了...");
						}else
						{
							// 执行原来的方法
							method.invoke(user, args);
						}
						
						return null;
					}
				});
		
		userproxy.save(); // 只要执行,就会执行增强业务方法invoke方法,这个方法里面就是对save方法的增强
		userproxy.delete(); // 只要执行,就会执行增强业务方法invoke方法,这个方法里面就是对delete方法的增强
	}
	
	
	// CGLIB的方式
	@Test
	public void test3()
	{
		// 目标类
		final Person person=new Person();
		
		// CGLIB的方式
		// 参数一: 目标类的字节码文件类型  因为用于继承
		// 参数二: 增强的业务逻辑
		Person p=(Person)Enhancer.create(Person.class,new MethodInterceptor() {
			
			@Override
			// 参数一: 代理对象的类型 固定值
			// 参数二: 目标类要增强的方法
			// 参数三: 方法运行时期需要的参数
			// 参数四: 代理方法 忽略
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy arg3) throws Throwable {
				
				if("delete".equals(method.getName()))
				{
					// 让原有方法执行
					System.out.println("之前增强....");
					method.invoke(person, args);
					System.out.println("之后增强....");
				}else
				{
					method.invoke(person, args);
				}
			
				return null;
			}
		});
		
		p.delete(); // 执行了它  增强业务intercept方法也会执行 这个方法里面就是对delete的增强
		p.find();// 执行了它  增强业务intercept方法也会执行 这个方法里面就是对find的增强
	}
	
	
	
	
}
