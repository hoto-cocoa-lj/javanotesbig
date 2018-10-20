package com.itheima.demo08_正则表达式;
/*
 * 正则表达式:
 * 		不是java的,是所有编程语言基本都会支持的
 * 
 * 1.正则表达式是什么?
 * 		是一个字符串,"正则表达式内容"
 * 		"普通的字符串":里面的内容就是内容
 * 		"正则表达式内容":里面写了规则
 * 	普通字符串仅仅表示内容,而正则表达式表示规则
 * 2.正则表达式有什么用?
 * 		用来匹配普通字符串的
 * 		boolean b = "普通字符串" 匹配  "正则表达式"
 * 3.代码实现:
 * 		在String类中
 * 		public boolean matches(String regex)
 * 		boolean b = "普通字符串".matches("正则表达式");
 * 4.练习1:举例：校验qq号码.
 * 	练习2举例：校验手机号码
 * 
 * 5.书写正则表达式的技巧:
 * 		5.1 正则表达式 需要一位一位的判断
 * 	
 * 
 * 6.在String类中有一个方法
 * 		public String[] split(String regex);//切割字符串,返回切割后的字符串数组
 */
public class RegexDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		split02();
	}
	/*
	 * QQ号码需要满足的规则:
	 * 1.必须是0-9的数字
	 * 2.开头必须1-9中的一个数字
	 * 3.位数必须 5-12位
	 */
	public static void qq(){
		String qq = "12355a63564";
		boolean b = qq.matches("[1-9][0-9]{4,11}");
		System.out.println(b);
	}
	/*
	 * 举例：校验手机号码
	 * 1：要求为11位0-9的数字
	 * 2：第1位为1，第2位为3、4、5、7、8中的一个，后面9位为0到9之间的任意数字。
	 */
	public static void phone(){
		String phone = "18600363521";
		boolean b = phone.matches("1[34578][0-9]{9}");
		System.out.println(b);
	}
	
	/*
	 * 
	 * 案例:切割电话
	 */
	public static void split01(){
		String phone = "2345--4564----6546-----1345";
		
		//切割phone  把号码切割出来
		//+号 在正则表达式中有特殊意义,表示连续的多个相同的字符
		String[] phones = phone.split("-+");
		for (int i = 0; i < phones.length; i++) {
			System.out.println(phones[i]);
		}
	}
	/*
	 * 案例:切割ip
	 */
	public static void split02(){
		String ip = "192...168....123......110";
		//"."号 在正则表达式中 表示 任意字符
		//转译字符
		//\t
		//\n
		String[] ips = ip.split("\\.+");//在正则表达式中 "\\" 代表一个"\"
		System.out.println(ips.length);
		for (int i = 0; i < ips.length; i++) {
			System.out.println(ips[i]);
		}
	}

}
