package com.gl.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.gl.utils.ManagerConnection;

public class ProductDao {
	public static void main(String[] args) throws SQLException {
		// update("insert t value(null,?,?)", "f", "25");
		// select("select * from t where tid=?", "2");
		// selectAll();
	}

	public static int update(String sql, String... param) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.update(ManagerConnection.getc(), sql, param);
	}

	public static int select(String sql, String... param) throws SQLException {
		QueryRunner qr = new QueryRunner();
		Object[] o = qr.query(ManagerConnection.getc(), sql, new ArrayHandler(), param);
		if (o.length == 0) {
			return 0;
		}
		System.out.println("[tid=" + o[0] + "\ttname=" + o[1] + "\tprice=" + o[2] + "]");
		return o.length;
	}

	public static void selectAll() throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from t";
		List<Object[]> l = qr.query(ManagerConnection.getc(), sql, new ArrayListHandler());
		for (Object[] o : l) {
			System.out.println("[tid=" + o[0] + "\ttname=" + o[1] + "\tprice=" + o[2] + "]");
		}
	}

}
