package com.zz.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.zz.utils.ConnectionManager;

public class AccountDao {
	// 转账和收钱
	public static void cAccount(String name, double money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update db set money=money+? where dname=?";
		Connection conn = ConnectionManager.getConnection();
		qr.update(conn, sql, money, name);
	}

	// 查询余额
	public static void select(String... s) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from db";
		Connection conn = ConnectionManager.getConnection();
		List<Object[]> l = qr.query(conn, sql, new ArrayListHandler());
		for (Object[] o : l) {
			System.out.println(Arrays.toString(o));
		}
	}

}
