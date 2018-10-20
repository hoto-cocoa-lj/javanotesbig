package utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ManagerConnection {
	public static ThreadLocal<Connection> t = new ThreadLocal();

	static public Connection getc() throws SQLException {
		// System.out.println("getc的线程是:" + Thread.currentThread());
		Connection c = t.get();
		if (c == null) {
			c = new ComboPooledDataSource().getConnection();
			t.set(c);
		}
		return c;
	}

	public static void start() throws SQLException {
		// TODO Auto-generated method stub
		getc().setAutoCommit(false);
	}

	public static void commit() throws SQLException {
		// TODO Auto-generated method stub
		getc().commit();
	}

	public static void rollback() throws SQLException {
		// TODO Auto-generated method stub
		getc().rollback();
	}

	public static void close() throws SQLException {
		// TODO Auto-generated method stub
		getc().close();
	}

}
