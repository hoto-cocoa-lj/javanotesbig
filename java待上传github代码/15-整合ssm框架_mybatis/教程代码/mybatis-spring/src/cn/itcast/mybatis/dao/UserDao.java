package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.pojo.User;

public interface UserDao {

	public User findById(int id);
	
	public void insertUser(User user);
}
