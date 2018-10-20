package com.domain;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//@Component("j")
public class JdbcTemplate1 {
	private JdbcTemplate j;

	public JdbcTemplate1() {
		JdbcTemplate j = new JdbcTemplate(new ComboPooledDataSource());
	}

}
