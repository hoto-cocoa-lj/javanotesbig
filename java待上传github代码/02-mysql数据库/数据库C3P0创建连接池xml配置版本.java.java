package com.itheima.demo2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//使用c3p0创建连接池，使用xml会自动寻找src下c3p0-config.xml来加载配置信息
public class Test {

	public static void main(String[] args) {

		String sql1 = "insert t value(null,?)";
		String sql2 = "update t set tname=? where tname=?";
		String sql3 = "delete from  t where tname=?";
		String sql4 = "select * from t";
		String s1 = "go";
		String s2 = "me";

		JDBCUtils.insert(sql1, s1);
		JDBCUtils.insert(sql1, s1);
		JDBCUtils.set(sql2, s2, s1);
		JDBCUtils.delete(sql3, s2);
		JDBCUtils.select(sql4);
	}
}

class JDBCUtils {
	static ComboPooledDataSource ds = new ComboPooledDataSource();

	public static Connection getc() throws SQLException {
		return ds.getConnection();

	}

	public static void closeAll(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}

			if (st != null) {

				st.close();
			}
			if (con != null) {

				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void update(String sql, String... ss) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtils.getc();
			st = con.prepareStatement(sql);

			for (int i = 0; i < ss.length; i++) {
				st.setObject(i + 1, ss[i]);
			}

			int t = st.executeUpdate();
			System.out.println("t=" + t);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, st, rs);
		}
	}

	public static void delete(String sql, String... ss) {
		update(sql, ss);
		System.out.println("删除成功");
	}

	public static void insert(String sql, String... ss) {
		update(sql, ss);
		System.out.println("插入成功");
	}

	public static void set(String sql, String... ss) {
		update(sql, ss);
		System.out.println("修改成功");
	}

	public static void select(String sql, String... ss) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			con = JDBCUtils.getc();
			st = con.prepareStatement(sql);
			if (ss != null) {
				for (int i = 0; i < ss.length; i++) {
					st.setObject(i + 1, ss[i]);
				}
			}

			rs = st.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getObject(2) + "\t" + rs.getObject(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, st, rs);
		}
	}

}