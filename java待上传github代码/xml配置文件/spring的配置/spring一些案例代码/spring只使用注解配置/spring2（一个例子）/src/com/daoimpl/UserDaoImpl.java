package com.daoimpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;

@Repository("userDaoImpl")
@Scope("singleton")
public class UserDaoImpl implements UserDao {

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("访问UserDaoimpl操作数据库");
	}

}
