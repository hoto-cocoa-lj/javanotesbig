package cn.itcast.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao
{
	@Autowired
	private HibernateTemplate  hibernateTemplate;
	
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	public void save(User user) {
		hibernateTemplate.save(user);
		
	}

}
