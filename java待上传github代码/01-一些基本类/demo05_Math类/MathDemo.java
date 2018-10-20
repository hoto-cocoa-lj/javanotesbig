package com.itheima.demo05_Math类;
/*
 * Math:数学类
 * 		public final class Math:这个类不能被继承 和 System一样
 * 
 * 该类不能创建对象,但是我们发现该类中所有的方法都是静态的
 * 
 * 1.public static double max(double d1,double d2);//求最大值
 * 2.public static double min(double d1,double d2);//求最小值
 * 
 * 
 * 3.public static double abs(double d);//取绝对值
 * 4.public static double random();//返回一个随机数,范围 [0,1)
 * 
 * 5.public static long round(double d);//四舍五入,只看小数的第一位
 * 6.public static double pow(double d1, double d2);//求次幂(次方)
 * 
 * 
 * 7.public static double ceil(double a);//ceil 天花板,向上取整
 * 8.public static double floor(double a);//floor 地板.向下取整
 * 
 * 
 */
public class MathDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.去绝对值
		double d = Math.abs(19.0);
		System.out.println(d);
		//2.获取伪随机数
		double r = Math.random();
		System.out.println(r);
		//3.四舍五入
		long m = Math.round(4.50000001);
		System.out.println(m);
		//4.求次幂
//		double p = Math.pow(3, 3);//3*3*3
//		System.out.println(p);
		double p2 = Math.pow(4, 2);
		System.out.println(p2);
		//5.ceil和floor
		double c1 = Math.ceil(4.0);
		double c2 = Math.floor(4.0);
		System.out.println(c1);
		System.out.println(c2);
	}

}
