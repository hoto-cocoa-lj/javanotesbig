package cn.itcast.ssm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.ssm.pojo.BaseDict;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.QueryVo;


public interface CustomerService {
	public List<Customer> findPage(QueryVo q);
	public int findTotal(QueryVo q);
	public Customer getById(long id);
	public void save(Customer c);
	public void delete(long id);
}
