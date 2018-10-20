package com.itheima.demo2;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
使用c3p0创建连接池，使用dbutils的无参数的QueryRunner创建qr，因为有参的qr每次用的connection不一样，
无法使用事务。无参的qr在调用update和query时，需要使用自己设置的connection

*/
public class Test {
	static Connection con;

	public static void main(String[] args) {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		QueryRunner qr = new QueryRunner();
		List<Object[]> list;

		try {
			con = ds.getConnection();
			list = qr.query(con, "select * from db", new ArrayListHandler());
			for (Object[] o : list) {
				System.out.println(Arrays.deepToString(o));
			}
			con.setAutoCommit(false);
			qr.update(con, "update db set money=money-100 where dname='b'");
			list = qr.query(con, "select * from db", new ArrayListHandler());
			for (Object[] o : list) {
				System.out.println(Arrays.deepToString(o));
			}
			System.out.println(1 / 0);
			qr.update(con, "update db set money=money+100 where dname='a'");
			con.commit();
			list = qr.query(con, "select * from db", new ArrayListHandler());
			for (Object[] o : list) {
				System.out.println(Arrays.deepToString(o));
			}
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("回滚....");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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