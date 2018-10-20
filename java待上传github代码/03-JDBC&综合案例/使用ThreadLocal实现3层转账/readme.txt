/*
ThreadLocal内部封装了一个map集合，key只能是是当前线程Thread.currentThread()，value是Object,
value也可以是Array，List等，所以可以用来存储很多信息。get()相当于Map.get(Thread.currentThread())，
set()方法也类似。每一个map都只能由当前线程访问和修改；
由于转账的代码全部在一个程序（线程），所以可以通过ThreadLocal来保存Connection

3层是指：
1：数据库操作层，即dao包，进行改查操作；
2：service包，调用dao的方法利用数据库进行转账，但不直接操作数据库；
3：view包，用户只需输入名字和金额即可完成转账；
1和2中间设置了ManagerConnection包，因为需要使用事务，1不方便用，2不能用（不能在2写数据库相关的代码）
*/

package com.itheima.demo2;
import java.sql.Connection;
import java.util.Arrays;
public class T {
	static Connection con;
	public static void main(String[] args) {
		ThreadLocal<String[]> t = new ThreadLocal<String[]>();
		new Thread() {
			public void run() {
				t.set("a b c".split("\\s"));
				System.out.println(this + " " + Arrays.toString(t.get()));
			}
		}.start();
		System.out.println(t.get());
	}
}
