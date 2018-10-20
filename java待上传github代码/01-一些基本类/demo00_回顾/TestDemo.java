package com.itheima.demo00_回顾;
/*
 * 异常处理的2种方式:
 * 1.声明抛出 throws
 * 2.捕获处理 try{...可能出现的代码} catch(Exception e){...}finally{...}
 * Object类,
 * 1.equals方法:
 * 			默认是比较的是地址,在Object类中 equals方法和 '=='是一样
 * 			我们可以重写equals方法,修改比较的规则,一般比较对象的成员变量值
 * 2.toString方法:
 * 			获取的该对象的字符串表示形式,默认的表示格式: 包名.类名@地址值
 * 			我们也可以重写toString方法,修改返回的字符串的格式,一般该对象的信息
 * 
 * 
 * 
 */
public class TestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建Dog对象
		Dog d1 = new Dog();//d1:0x111
		d1.age = 10;
		Dog d2 = new Dog();//d2:0x222
		d2.age = 20;
	/*	boolean b1 = d1.equals(d2);
		boolean b2 = (d1 == d2);
		
		System.out.println(b1);
		System.out.println(b2);*/
		
		
		System.out.println(d2);//打印一个对象时,JVM会先调用对象的toString方法
		//System.out.println(d1.toString());
		
	}

}
