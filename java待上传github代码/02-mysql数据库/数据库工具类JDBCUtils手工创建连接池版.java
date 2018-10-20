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

*/
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
		System.out.println("hehe" + JDBCUtils.cons.size());
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
		//return cons.get(0);
		//不能使用get(0)，会报错No operations allowed after connection closed.
		return cons.remove(0);
		//每次移除一个，因为该连接使用后会被关闭不能多次使用

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
		System.out.println("cons.size()=" + cons.size());
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
			boolean x = false;
			while (rs.next()) {
				System.out.println(rs.getObject(2) + "\t" + rs.getObject(1));
				x = true;
			}
			if (x) {
				System.out.println("登陆成功！！");
			} else {
				System.out.println("登陆失败！！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(con, st, rs);
		}
	}

}


/*
//config.properties
url = jdbc:mysql://localhost:3306/forpython
dr = java.sql.Driver
user = root
pw=				//这一行可以注释掉，也可以不注释
*/