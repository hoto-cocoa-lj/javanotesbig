package com.itheima.demo02_日期格式化类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * DateFormat:日期格式化类
 * 
 * 1.DateFormat是一个抽象类 我们不能用它
 * 
 * 2.我们使用它的一个子类:SimpleDateFormat 
 *
 * SimpleDateFormat:简单的日期格式化类
 * 
 * 1.构造:
 * 		public SimpleDateFormat(String pattern);//以指定的模式 创建一个日期格式化对象
 * 2.成员方法:
 * 		public String format(Date d);//格式化一个日期对象,返回格式化后的一个字符串
 * 		public Date parse(String s);//把一个字符串形式的日期,解析成Date对象
 * 		注意:
 * 		public String format(Date d);把日期对象转成字符串形式的日期
 * 		public Date parse(String s);把字符串形式的日期,转回成日期对象
 * 		
 * 	
 */
public class DateFormatDemo {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
//		demo01();
		demo02();
	}
	//解析日期对象
	public static void demo02() throws ParseException{
		//1.创建SimpleDateFormat对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH点mm分ss秒");
		//2.使用sdf解析一个字符串
		Date d = sdf.parse("2017年10月06日  20点18分25秒");
		//3.打印
		System.out.println(d);
	}
	//格式化日期对象
	public static void demo01(){
		//1.创建SimpleDateFormat对象
		//比如:我们要把日期格式化成 2000年10月20日 20点18分55秒
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH点mm分ss秒");
		//2.创建一个日期对象
		Date d = new Date();
		//3.使用sdf 来格式化日期对象
		String s = sdf.format(d);
		System.out.println(s);
	}
}
