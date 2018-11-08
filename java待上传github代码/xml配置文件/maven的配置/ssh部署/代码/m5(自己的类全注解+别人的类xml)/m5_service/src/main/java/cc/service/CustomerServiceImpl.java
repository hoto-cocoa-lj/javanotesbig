package cc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.dao.CustomerDao;
import cc.domain.Customer;
@Component("cs")
public  class CustomerServiceImpl  implements CustomerService{
	@Autowired
private CustomerDao cd;
	public List<Customer> a() {

		return cd.a();
	}


}
