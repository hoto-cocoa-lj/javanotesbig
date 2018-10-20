package com.itheima.demo2;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/*
防sql注入,带配置文件,自制连接池版本；
用preparedStatement实现防sql注入；
用config文件+properties.load实现用配置文件进行配置；
用 ArrayList<Connection> cons 实现自制连接池；

con.setAutoCommit(false);con.commit();con.rollback();进行事务操作
需要在同一个connection里

*/

public class Test {

	public static void main(String[] args) {
		Connection con = null;
		try {
			con = JDBCUtils.getc();
			con.setAutoCommit(false);
			JDBCUtils.select(con, "select * from db");

			JDBCUtils.update(con, "update db set money=money+100 where dname='a'");
			JDBCUtils.select(con, "select * from db");
			System.out.println(1 / 0);
			JDBCUtils.update(con, "update db set money=money-100 where dname='b'");
			con.commit();
			JDBCUtils.select(con, "select * from db");
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("回滚....");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
}

class JDBCUtils {
	static String url = null;
	static String dr = null;
	static String user = null;
	static String pw = null;
	static ArrayList<Connection> cons = new ArrayList<Connection>();
	static int size = 8;
	static {
		try {
			Properties ps = new Properties();
			ps.load(new FileInputStream("config.properties"));
			url = ps.getProperty("url");
			dr = ps.getProperty("dr");
			user = ps.getProperty("user");
			pw = ps.getProperty("pw");
			Class.forName(dr);
			for (int i = 0; i < size; i++) {
				Connection c = DriverManager.getConnection(url, user, pw);
				cons.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("驱动加载失败，程序将停止");
		}
	}

	public static Connection getc() throws SQLException {
		// return DriverManager.getConnection(url, user, pw);
		// return cons.get(0);
		// 不能使用get(0)，会报错No operations allowed after connection closed.
		return cons.remove(0);
		// 每次移除一个，因为该连接使用后会被关闭不能多次使用

	}

	public static void closeAll(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}

			if (st != null) {

				st.close();
			}
			// if (con != null) {
			//
			// con.close();
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void update(Connection con, String sql, String... ss) {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);

			for (int i = 0; i < ss.length; i++) {
				st.setObject(i + 1, ss[i]);
			}

			int t = st.executeUpdate();
			if (sql.startsWith("delete")) {
				System.out.println("删除" + t + "条记录成功");
			} else if (sql.startsWith("select")) {
				System.out.println("插入" + t + "条记录成功");
			} else if (sql.startsWith("update")) {
				System.out.println("修改" + t + "条记录成功");
			} else {
				System.out.println(sql + t + "条记录成功");
			}
			System.out.println("连接池还有" + cons.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, st, rs);
		}
	}

	public static void select(Connection con, String sql, String... ss) {

		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			if (ss != null) {
				for (int i = 0; i < ss.length; i++) {
					st.setObject(i + 1, ss[i]);
				}
			}

			rs = st.executeQuery();

			while (rs.next()) {
				for (int i = 1; i < 4; i++) {

					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();

			}
			System.out.println("连接池还有" + cons.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, st, rs);
		}
	}

}

/*
 * //config.properties url = jdbc:mysql://localhost:3306/forpython dr =
 * java.sql.Driver user = root pw= //这一行可以注释掉，也可以不注释
 */