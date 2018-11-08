package cn.itcast.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;

@Transactional
public class CustomerServiceImpl implements CustomerService{
private CustomerDao cd;

public void setCd(CustomerDao cd) {
	this.cd = cd;
}

public List<Customer> findAll() {
	Customer i = cd.findbyid(27L);
	i.setCust_phone("22");
	cd.update(i);
//	System.out.println(1/0);
	return cd.findAll();
}

}
