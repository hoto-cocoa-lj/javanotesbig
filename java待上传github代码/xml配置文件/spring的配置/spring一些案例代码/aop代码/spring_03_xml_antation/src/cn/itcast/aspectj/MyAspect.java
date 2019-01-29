package cn.itcast.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 切面类--有增强方法
@Component("myAspect")
@Aspect
public class MyAspect 
{
	// 增强方法
	@Before(value ="execution(* cn.itcast.domain.Person.save(..))")
	public void beforeMethod()
	{
		System.out.println("-----beforeMethod-----");
	}
	
	// 增强方法
	@AfterReturning(value="execution(* cn.itcast.domain.Person.delete(..))")
	public void aftereturningMethod()
	{
		System.out.println("----aftereturningMethod---");
	}
	
	// 增强方法
	@Around(value="execution(* cn.itcast.domain.Person.find(..))")
	public void aroundMethod(ProceedingJoinPoint pdp) throws Throwable // 正在要执行的原有方法
	{
		System.out.println("之前...");
		// 原有方法执行一下
		pdp.proceed(); // method.invoke();
		System.out.println("之后...");
	}
	
	// 增强方法
	@AfterThrowing(value="execution(* cn.itcast.domain.Person.update(..))")
	public void throwingMethod()
	{
		System.out.println("----throwingMethod----");
	}
	
	// 增强方法
	@After(value="execution(* cn.itcast.domain.Person.update(..))")
	public void afterMethod()
	{
		System.out.println("--不管你有没有异常,我都出来了---");
	}
}
