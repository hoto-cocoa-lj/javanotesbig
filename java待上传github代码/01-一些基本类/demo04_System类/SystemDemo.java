package com.itheima.demo04_System类;
/*
 * System:系统类
 * 		它不能被实例化。 因为构造方法私有化了
 * 		而且System类中的方法都是静态的,通过类名就可以直接访问
 * System类中几个常用方法:
 * 		public static void exit(int code);//退出JVM
 * 		public static void gc();//运行java的垃圾回收器(并不是马上运行)
 * 		public static String getProperty(String PropertyName);//获取某一个属性的值
 * 
 * 		//偶尔用得到
 * 		public static long currentTimeMillis();//获取当前系统时间的毫秒值
 * 
 */
public class SystemDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//获取当前系统时间的毫秒值
		//1.通过Date的getTime
		//2.通过Calendar的getTime,获取到Date,再调用getTime()
		//3.最常用的方式
//		long time = System.currentTimeMillis();
//		System.out.println(time);
		//String(不可变) 和  StringBuilder(可变)
		//当出现大量的字符串拼接运算 时 用StringBuilder
		
		long start = System.currentTimeMillis();//2046
		//使用字符串拼接
		/*String s = "";
		for(int i = 0;i<30000;i++){
			s+=i;
		}*/
		//使用StringBuilder拼接
		StringBuilder sb = new StringBuilder();//2
		for(int i = 0;i<300000;i++){
			sb.append(i);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		
		
		
		
		
		
		
	}
	
	public static void demo01(){
		//1.创建一个System对象
				//System s = new System();//不能创建对象
				//2.exit方法
				/*for (int i = 0; i < 10; i++) {
					System.out.println(i);
					System.exit(0);//退出JVM
				}*/
				//3.获取属性
				/*String s = System.getProperty("user.name");
				System.out.println(s);*/
	}

}
