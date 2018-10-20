package com.daoimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dao.AccountDao;
import com.domain.T;

@Component("adi")
public class AccountDaoImpl implements AccountDao {
	@Autowired
	@Qualifier(value = "j")
	private JdbcTemplate j;

	@Override
	public void t1() {
		String s = "insert t(tname,price) value(?,?) ";
		Double d = Math.random() * 100;
		j.update(s, new Date().toLocaleString(), d.intValue());
	}

	@Override
	public void t2() {
		String s = "delete from t where tid=?";
		j.update(s, 8);
	}

	@Override
	public void t3() {
		String s = "update t set price=? where price=?";
		j.update(s, 3, 33);
	}

	@Override
	public void t4() {
		// String s = "select * from t";
		// List l = j.query(s, new BeanPropertyRowMapper(T.class));
		String s = "select * from t where price=?";
		List l = (List) j.query(s, new BeanPropertyRowMapper(T.class), 100);
		System.out.println(l);

	}
}