package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Customer;

public interface CustomerDao {
	public List<Customer> findAll();
	public void update(Customer c);
	public Customer findbyid(Long l);
}
