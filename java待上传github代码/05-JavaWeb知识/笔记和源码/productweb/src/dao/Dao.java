package dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bean.product;
import utils.ManagerConnection;

public class Dao {
	static String cql1 = "select count(*) from p";
	static String cql4 = "select count(*) from p where name like ? or date  like binary ?";
	static String sql1 = "select * from p limit ?,?";
	static String sql3 = "delete from p where uid=?";
	static String sql2 = "insert p(uid,name,price,date,img) value(?,?,?,?,?)";
	static String sql6 = "update p set price=?,date=?,img=? where name=?";
	static String sql4 = "select * from p where name like ? or date  like binary ? limit ?,?";
	static String sql5 = "select * from p where name=?";

	// public long count() throws SQLException {
	// QueryRunner qr = new QueryRunner();
	// return (long) qr.query(new ManagerConnection().getc(), cql1, new
	// ScalarHandler());
	// }

	public long count(String... searchword) throws SQLException {
		QueryRunner qr = new QueryRunner();
		ScalarHandler sh = new ScalarHandler();
		if (searchword != null & searchword.length > 0) {
			return (long) qr.query(new ManagerConnection().getc(), cql4, sh, searchword[0], searchword[1]);
		} else {
			return (long) qr.query(new ManagerConnection().getc(), cql1, sh);
		}
	}

	// public List<product> selects(int page, int onepage) throws SQLException {
	// QueryRunner qr = new QueryRunner();
	// BeanListHandler<product> bl = new
	// BeanListHandler<product>(product.class);
	// return qr.query(new ManagerConnection().getc(), sql1, bl, page, onepage);
	// }

	public List<product> selects(int page, int onepage, String... searchword) throws SQLException {
		QueryRunner qr = new QueryRunner();
		BeanListHandler<product> bl = new BeanListHandler<product>(product.class);
		if (searchword != null && searchword.length > 0) {
			return qr.query(new ManagerConnection().getc(), sql4, bl, searchword[0], searchword[1], page, onepage);
		} else {
			return qr.query(new ManagerConnection().getc(), sql1, bl, page, onepage);
		}
	}

	public int insert(String... u) throws SQLException {
		QueryRunner qr = new QueryRunner();
		System.out.println("数据库insert前。name=" + Arrays.toString(u));
		return qr.update(new ManagerConnection().getc(), sql2, u);
	}

	public int update(String... u) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.update(new ManagerConnection().getc(), sql6, u);

	}

	public int delete(String i) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner();
		return qr.update(new ManagerConnection().getc(), sql3, i);
	}

	public boolean isnamein(String i) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner();
		// System.out.println("isnameinde i =" + i);
		Object[] xxx = qr.query(new ManagerConnection().getc(), sql5, new ArrayHandler(), i);
		boolean b = (xxx != null && xxx.length > 0);
		return b;
	}

}
