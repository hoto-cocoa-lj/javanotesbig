package com.itheima.demo03_Calendar日历类;

import java.util.Calendar;
import java.util.Date;

/*
 * Calendar:也是代表特定的瞬间,年月日时分秒
 * 
 * 由于Calendar是一个抽象类,我们不能直接用
 * 
 * 寻找它的子类:
 * 		GregorianCalendar 我们看都看不懂
 * 
 * 查找Calendar的静态方法
 * 		public static Calendar getInstance();//获取Calendar的具体的某个子类的对象
 * 
 * 成员方法:
 * 		****public int get(int field);//获取Calendar对象中的某一个字段/成员变量的值
 * 		public void add(int field,int amount);//给指定的字段 添加指定的值
 * 		public void set(int field,int value);// 直接修改指定字段的值
 * 		public Date getTime();//返回该Calendar对象对应的Date对象
 * 				Date也有getTime(),获取Date对象对应的毫秒值
 * 
 */
public class CalendarDemo01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.获取Calendar的子类对象
		Calendar c = Calendar.getInstance();//获取对象
		//2.打印c的年月日
		printNYR(c);
		//3.给c中的年 添加1
//		c.add(Calendar.YEAR, 1);
		//4.给c中的月添加1
//		c.add(Calendar.MONDAY, 1);
		//5.乱加
//		c.add(Calendar.MONTH, 30);
		//c.add(Calendar.DAY_OF_MONTH, 145);
		//6.修改指定字段的值
//		c.set(Calendar.YEAR, 1000);
//		c.set(Calendar.MONTH, 10);
//		c.set(Calendar.DAY_OF_MONTH, 10);
		printNYR(c);
		//6.getTime方法,把Calendar对象转成Date对象
//		Date d = c.getTime();
//		System.out.println(d);
		//7.如何获取c的距离标准时间的毫秒值
//		System.out.println(c.getTime().getTime());
		
	}
	//封装一个方法  获取一个日历对象的年月日
	public static void printNYR(Calendar c){
		//2.获取Calendar对象的某个字段
		int year = c.get(Calendar.YEAR);
		
		int month = c.get(Calendar.MONTH);
		
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		System.out.println(year+"年"+(month+1)+"月"+day+"日");
	}

}
