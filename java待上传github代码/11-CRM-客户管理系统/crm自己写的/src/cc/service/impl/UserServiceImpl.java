package cc.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cc.dao.UserDao;
import cc.domain.BaseDict;
import cc.domain.Customer;

import cc.service.UserService;

@Component("us")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
private UserDao ud;

	@Override
	public void save(Customer c) {
		ud.save(c);
		
	}

	@Override
	public List<BaseDict> findcid(String s) {
		// TODO Auto-generated method stub
		return ud.findcid(s) ;
	}

	@Override
	public List<Customer> findall() {
		return ud.findall();
		
	}

	@Override
	public List<Customer> xs(Customer c) {
		// TODO Auto-generated method stub
		return ud.xs(c);
	}

	@Override
	public void delete(Long deletecid) {
		ud.delete(deletecid);
		
	}

	@Override
	public List<Customer> xs(DetachedCriteria c) {
		return ud.xs(c);
	
	}

	@Override
	public Customer findById(Long deletecid) {
		// TODO Auto-generated method stub
		return ud.findById(deletecid);
	}

	@Override
	public void update(Customer c1) {
		ud.update(c1);
		
	}

}
