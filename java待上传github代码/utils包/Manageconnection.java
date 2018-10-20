package com.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Manageconnection {
	ThreadLocal<Connection> t = new ThreadLocal<Connection>();

	public Connection getc() throws SQLException {
		Connection c = t.get();
		if (c == null) {
			c = new ComboPooledDataSource().getConnection();
			t.set(c);
		}
		return c;
	}

	public void start() throws SQLException {
		getc().setAutoCommit(false);
	}

	private void commit() throws SQLException {
		// TODO Auto-generated method stub
		getc().commit();
	}

	private void rollback() throws SQLException {
		// TODO Auto-generated method stub
		getc().rollback();
	}

	public void close() throws SQLException {
		// TODO Auto-generated method stub
		getc().close();
	}
}
