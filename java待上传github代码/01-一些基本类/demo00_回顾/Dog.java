package com.itheima.demo00_回顾;

public class Dog{
	int age;
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//修改比较规则,改层比较年龄
		Dog d = (Dog)obj;
		if(this.age == d.age){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "这是一条Dog,age="+age;
	}
}
