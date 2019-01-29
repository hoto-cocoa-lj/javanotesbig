package cn.me.dao;

import cn.me.domain.User;

public interface UserDao {
	public void save(User u);

	public User get(Long l);
}
