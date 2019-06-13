package com.itheima.demo2;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Map<String, Float> a = new LinkedHashMap<String, Float>();
		File[] f = new File("H:\\黑马java淘宝68元").listFiles();

		for (File x : f) {
			float i = 0;
			if (x.isFile()) {
				continue;
			}
			// System.out.println(x);
			for (File y : x.listFiles()) {
				if (y.isFile()) {
					i += 0.01;
				} else if (y.isDirectory()) {
					i += 1;
				}
			}
			a.put(x.getName(), i);
		}
		float l = 0;
		for (String s : a.keySet()) {
			float i = a.get(s);
			System.out.println(s + ":" + i);
			l += i;
		}
		System.out.println(l);
	}

}

/*
01-Java进阶:15.0
02-mysql数据库:3.0
03-JDBC&综合案例:4.0
04-Web前端知识:7.01
05-JavaWeb知识:13.0
06-JavaWeb技术加强:4.0
07-store项目:6.0
08-Hibernate框架:4.0
09-Struts2框架:4.0
10-Spring框架:5.0
11-CRM-客户管理系统:3.0
12-Oracle数据库:4.0
13-Maven:2.0
14-国际物流云商项目:15.0
15-整合ssm框架_mybatis:2.0
16-SpringMvc_SSM综合实战:3.0
17-Lucene&solr入门&进阶:3.0
18-大型分布式电商项目:20.0
117.01
*/