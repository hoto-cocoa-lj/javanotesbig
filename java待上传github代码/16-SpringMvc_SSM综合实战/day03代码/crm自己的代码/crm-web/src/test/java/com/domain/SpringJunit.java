package com.domain;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import cn.itcast.common.utils.PageBean;
import cn.itcast.ssm.pojo.BaseDict;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.service.BaseDictService;
import cn.itcast.ssm.service.CustomerService;

import cn.itcast.common.utils.PageBean;
//两个类测试的结果一样，说明使用同一个ApplicationContext对象
//(ClassPathXmlApplicationContext/FileSystemXmlApplicationContext)
		


@ContextConfiguration(value = "classpath:applicationContext*.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
//	@Autowired
//	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	@Test
	public void list(){	
		System.out.println("goode");
		int currentPage=1;
		int pageSize=3;
//		PageBean pagebean = new PageBean();
//		pagebean.setCurrentPage(currentPage);
//		Double p= currentPage*1.0/pageSize;
//		int totalCount=(int) Math.ceil(p);
//		
//		int from=(currentPage-1)*pageSize;
//		int to=currentPage*pageSize;
//		List<Customer> list = customerService.findPage(from, to);
//		pagebean.setList(list);
//		pagebean.setPageSize(pageSize);
//		pagebean.setTotalCount(totalCount);

	}
}
