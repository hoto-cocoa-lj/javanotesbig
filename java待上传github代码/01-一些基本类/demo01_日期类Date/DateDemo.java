package com.itheima.demo01_日期类Date;

import java.util.Date;

/*
 * Date类:日期类
 * 		表示特定的瞬间，精确到毫秒,(1秒=1000毫秒)
 * 
 * 构造:
 * 	****public Date();//无参构造,创建一个代表当前系统时间的Date对象
 * 	public Date(long time);//以指定的毫秒值创建一个Date对象
 * 					毫秒值代表距离 标准时间(1970.01.01 00:00:00)的毫秒值
 * 成员方法:
 * 	toString();//是Date类重写了Object类的toString:星期几 月份 日 时:分:秒  CST 年
 * 	public long getTime();//返回的是当前的Date对象,距离标准时间的毫秒值
 * 注意:
 * 	public Date(long time);//把毫秒值 转成了Date对象
 * 	public long getTime();//把Date对象 转成毫秒值
 * 
 * 
 */
public class DateDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.创建一个Date对象
		//Date d1 = new Date();
		
		//System.out.println(d1);//打印结果说明了Date类重写了toString方法
		//打印结果:Fri Oct 06 08:54:33 CST 2017
		//2.创建一个Date对象,使用毫秒值
		/*Date d2 = new Date(0);
		System.out.println(d2);*/
		/*Date d3 = new Date(1501383854384L);
		System.out.println(d3);*/
		//3.返回当前Date对象距离标准时间的毫秒值
		Date d4 = new Date();
		long time =  d4.getTime();
		System.out.println(time);//1507252128450
		
		
	}

}
