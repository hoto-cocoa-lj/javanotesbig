package com.itheima.demo2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.domain.Cato;
import com.mchange.v2.c3p0.ComboPooledDataSource;

//使用C3P0框架创建连接池，使用DBUtils框架进行增删改查；

public class JDBCUtils {
	public static void main(String[] args) throws SQLException {

		String sql1 = "insert t value(null,?)";
		String sql2 = "update t set tname=? where tname=?";
		String sql3 = "delete from t where tname=?";
		String sql4 = "select * from t where tid=?";
		String s1 = "go";
		String s2 = "me";
		JDBCUtils.insert(sql1, s1);
		JDBCUtils.insert(sql1, s1);
		JDBCUtils.set(sql2, s2, s1);
		JDBCUtils.delete(sql3, s2);
		System.out.println("-------------------");
		JDBCUtils.selectArrayHandler("select * from t where tid>? and tname like ?", "5", "%u%");
		System.out.println("-------------------");
		JDBCUtils.selectArrayListHandler("select * from t where tid>?", "3");
		System.out.println("-------------------");
		JDBCUtils.selectBeanHandler("select * from t where tid>?", "3");
		System.out.println("-------------------");
		JDBCUtils.selectBeanListHandler("select * from t where tid>?", "3");
		System.out.println("-------------------");
		JDBCUtils.selectColumnListHandler("select * from t where tid>?", "3");
		System.out.println("-------------------");
		JDBCUtils.selectMapHandler("select * from t where tid>?", "3");
		System.out.println("-------------------");
		JDBCUtils.selectMapListHandler("select * from t where tid>?", "3");
		System.out.println("-------------------");
		JDBCUtils.selectScalarHandler("select max(tid) from t where tid>?", "3");
		System.out.println("-------------------");

	}

	private static ComboPooledDataSource ds = new ComboPooledDataSource();

	// 返回C3P0创建的连接池
	public static DataSource getds() throws SQLException {
		return ds;

	}

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

		try {
			QueryRunner qr = new QueryRunner(JDBCUtils.getds());
			System.out.println(qr.update(sql, ss));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	// 将ResultSet的第一条数据封装到Object[]数组里，数组每一个元素是该数据的每一个字段的值
	public static void selectArrayHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		Object[] objs = qr.query(sql, new ArrayHandler(), ss);
		System.out.println(Arrays.toString(objs));

	}

	// 将ResultSet的每一条数据封装到各自的Object[]数组里，然后将数组封装到list并返回
	public static void selectArrayListHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), ss);
		for (Object[] o : list) {
			System.out.println(Arrays.toString(o));
		}
	}

	// 重要：将ResultSet的每一条数据封装到javaBean里
	public static void selectBeanHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		// 这个Cato类必须和数据库的字段的名字和类型一一对应
		Cato c = qr.query(sql, new BeanHandler<Cato>(Cato.class), ss);
		System.out.println(c + " " + c.getTid() + " " + c.getTname());

	}

	// 将ResultSet的每一条数据封装到javaBean里
	public static void selectBeanListHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		// 这个Cato类必须和数据库的字段的名字和类型一一对应
		List<Cato> c = qr.query(sql, new BeanListHandler<Cato>(Cato.class), ss);
		System.out.println(c);

	}

	// 将ResultSet的每一条数据的指定列封装到list里
	public static void selectColumnListHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		List lc = qr.query(sql, new ColumnListHandler<>("tname"), ss);
		System.out.println(lc);

	}

	// 将ResultSet的每一条数据封装到Map<String,Object>里，key是字段名value是字段值
	public static void selectMapHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		Map m = qr.query(sql, new MapHandler(), ss);
		System.out.println(m);

	}

	// 将ResultSet的每一条数据的封装到Map<String,Object>里，再把map封装到list返回
	public static void selectMapListHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		List lm = qr.query(sql, new MapListHandler(), ss);
		System.out.println(lm);

	}

	// 用于单个数据，比如count(*)
	public static void selectScalarHandler(String sql, String... ss) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getds());
		Long lm = qr.query(sql, new ScalarHandler<Long>(), ss);
		System.out.println(lm);

	}
}