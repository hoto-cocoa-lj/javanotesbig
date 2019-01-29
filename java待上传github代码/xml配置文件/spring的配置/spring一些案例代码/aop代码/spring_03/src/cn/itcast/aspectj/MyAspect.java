package cn.itcast.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;

// 切面类--有增强方法
public class MyAspect 
{
	// 增强方法
	public void beforeMethod()
	{
		System.out.println("-----beforeMethod-----");
	}
	
	// 增强方法
	public void aftereturningMethod()
	{
		System.out.println("----aftereturningMethod---");
	}
	
	// 增强方法
	public void aroundMethod(ProceedingJoinPoint pdp) throws Throwable // 正在要执行的原有方法
	{
		System.out.println("之前...");
		// 原有方法执行一下
		pdp.proceed(); // method.invoke();
		System.out.println("之后...");
	}
	
	// 增强方法
	public void throwingMethod()
	{
		System.out.println("----throwingMethod----");
	}
	
	// 增强方法
	public void afterMethod()
	{
		System.out.println("--不管你有没有异常,我都出来了---");
	}
}
