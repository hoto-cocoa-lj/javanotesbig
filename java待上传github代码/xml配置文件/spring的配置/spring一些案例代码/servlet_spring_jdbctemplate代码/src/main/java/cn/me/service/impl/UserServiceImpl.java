package cn.me.service.impl;

import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.me.dao.UserDao;
import cn.me.domain.User;
import cn.me.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao ud;
	TransactionManager t;

	@Override
	public void save(User u) {
		Integer c = u.getUser_code();
		User u1 = ud.get(1L);
		User u2 = ud.get(2L);
		u1.setUser_code(u1.getUser_code() + c);
		u2.setUser_code(u2.getUser_code() - c);
		System.out.println(u1 + "\t" + u2);
		ud.save(u1);
		System.out.println(1 / 0);
		ud.save(u2);
	}

}
