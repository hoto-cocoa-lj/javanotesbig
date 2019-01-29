package cn.me.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.me.dao.UserDao;
import cn.me.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate j;

	@Override
	public void save(User u) {
		String sql = "update  sys_user set user_code=? where user_id=?";
		j.update(sql, u.getUser_code(), u.getUser_id());

	}

	@Override
	public User get(Long l) {
		String sql = "select * from sys_user where user_id=?";
		BeanPropertyRowMapper<User> b = new BeanPropertyRowMapper<User>(User.class);
		User u = j.queryForObject(sql, b, l);
		return u;
	}

}
