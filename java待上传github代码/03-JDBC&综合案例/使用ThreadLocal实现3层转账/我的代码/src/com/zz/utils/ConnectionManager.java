package com.zz.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {

		Connection conn = t.get();
		if (conn == null) {
			conn = new ComboPooledDataSource().getConnection();
			t.set(conn);
		}
		return conn;
	}

	public static void start() throws SQLException {
		getConnection().setAutoCommit(false);
	}

	public static void commit() throws SQLException {
		getConnection().commit();
	}

	public static void rollback() throws SQLException {
		getConnection().rollback();
	}

	public static void close() throws SQLException {
		getConnection().close();
	}

}
