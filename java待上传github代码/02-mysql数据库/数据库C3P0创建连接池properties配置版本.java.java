package com.itheima.demo2;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//使用c3p0创建连接池，这里使用properties文件保存和读取配置，正常情况用xml保存和读取
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

	static {
		try {
			Properties ps = new Properties();
			
			//这里只能用config.properties配置文件
			String n = "config.properties";
			String n1 = "dbcpconfig.properties";
			ps.load(new FileInputStream(n)); // 配置文件在根目录
			System.out.println(Test.class + " " + JDBCUtils.class);
			// 配置文件在src
			// ps.load(Test.class.getClassLoader().getResourceAsStream(n));
			String url = ps.getProperty("url");
			String user = ps.getProperty("user");
			String pw = ps.getProperty("pw");
			String dr = ps.getProperty("dr");
			ds.setJdbcUrl(url);
			ds.setUser(user);
			ds.setPassword(pw);
			ds.setDriverClass(dr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("驱动加载失败，程序将停止");
		}
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

/*
//config.properties
url = jdbc:mysql://localhost:3306/forpython
dr = java.sql.Driver
user = root
pw=				//这一行可以注释掉，也可以不注释
*/


/*
dbcpconfig.properties
#连接设置 ,必须配置的
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/forpython
username=root
#password=				//这一行可以注释掉，也可以不注释

#<!-- 初始化连接 -->
initialSize=10

#最大连接数量
maxActive=50

#<!-- 最大空闲连接 -->
maxIdle=20

#<!-- 最小空闲连接 -->
minIdle=5

#<!-- 超时等待时间以毫秒为单位 60000毫秒/1000等于60秒 -->
maxWait=60000


#JDBC驱动建立连接时附带的连接属性属性的格式必须为这样：[属性名=property;] 
#注意："user" 与 "password" 两个属性会被明确地传递，因此这里不需要包含他们。
connectionProperties=useUnicode=true;characterEncoding=utf8

#指定由连接池所创建的连接的自动提交（auto-commit）状态。
defaultAutoCommit=true

#driver default 指定由连接池所创建的连接的只读（read-only）状态。
#如果没有设置该值，则“setReadOnly”方法将不被调用。（某些驱动并不支持只读模式，如：Informix）
defaultReadOnly=

#driver default 指定由连接池所创建的连接的事务级别（TransactionIsolation）。
#可用值为下列之一：（详情可见javadoc。）NONE,READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
defaultTransactionIsolation=REPEATABLE_READ

*/