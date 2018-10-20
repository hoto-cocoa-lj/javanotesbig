package com.gl.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ManagerConnection {
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();

	public static Connection getc() throws SQLException {
		Connection con = t.get();
		if (con == null) {
			con = new ComboPooledDataSource().getConnection();
			t.set(con);
		}
		return con;
	}

	public static void start() throws SQLException {
		getc().setAutoCommit(false);
	}

	public static void commit() throws SQLException {
		getc().commit();
	}

	public static void close() throws SQLException {
		getc().close();

	}

	public static void rollback() throws SQLException {
		getc().rollback();
	}
}
