package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.pojo.User;

public interface UserMapper {
    
	public User findById(int id);
	
//	public void insertUser(User user);
}
