package cc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import cc.dao.UserDao;
import cc.domain.BaseDict;
import cc.domain.Customer;

@Component("ud")
public class UserDaoImpl implements UserDao{
	@Autowired
private HibernateTemplate h;


	@Override
	public void save(Customer c) {
		h.save(c);
		
	}

	@Override
	public List<BaseDict> findcid(String s) {
		return (List<BaseDict>) h.find("from BaseDict  where dict_type_code=?",s);
	}

	@Override
	public List<Customer> findall() {
		// TODO Auto-generated method stub
		return (List<Customer>) h.find("from Customer");
	}

	@Override
	public List<Customer> xs(Customer c) {
		String s="from Customer where cust_level=? and cust_industry=? and cust_source=?";
		BaseDict l3=c.getCust_level();
		BaseDict l2=c.getCust_industry();
		BaseDict l1=c.getCust_source();
		List<Customer> l=(List<Customer>) h.find(s,l3,l2,l1);
		return l;
	}

	@Override
	public void delete(Long deletecid) {
		Customer o=h.get(Customer.class, deletecid); 
		h.delete(o);
		
	}

	@Override
	public List<Customer> xs(DetachedCriteria c) {
		return (List<Customer>) h.findByCriteria(c);

	}

	@Override
	public Customer findById(Long deletecid) {
		return h.get(Customer.class, deletecid);
	}

	@Override
	public void update(Customer c1) {
		h.update(c1);
		
	}
	
}
