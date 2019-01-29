package cn.itcast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	@Transactional
	public void save(User user) {
		userDao.save(user);
		
	}

}
