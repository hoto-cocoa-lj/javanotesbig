package cn.itcast.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.itcast.domain.Customer;

public class CustomerDaoImpl implements CustomerDao{
	private HibernateTemplate h;

	public void setH(HibernateTemplate h) {
		this.h = h;
	}


	public void update(Customer c){
		h.update(c);
	}
	public Customer findbyid(Long l){
		return h.get(Customer.class, l);
	}


	public List<Customer> findAll() {
		return (List<Customer>) h.find("from Customer");
	}

}
