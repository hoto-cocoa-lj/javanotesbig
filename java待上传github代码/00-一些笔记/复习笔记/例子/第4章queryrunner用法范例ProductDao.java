注意delete的两个方法，由于需要同一个connection，我的写法是delete方法有connection参数，然后service层通过ConnectionManager.getConnection获取后传给它，教程的写法是不用这个参数，而是直接写在方法里，因为它们在运行时在同一个线程，显然这样更好。


package com.itheima.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.domain.Product;
import com.itheima.utils.C3P0Utils02;
import com.itheima.utils.ConnectionManager;

/**
 * 商品Dao层
 * @author yingpeng
 *
 */
public class ProductDao {
	//批量删除
	public void deleteById(int id) throws SQLException{
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner();
		//2调动qr的update
		qr.update(ConnectionManager.getConnection(),"delete from products where pid = ?", id);
	}
	//根据id删除商品
	public void deleteByOneId(int id) throws SQLException{
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner(C3P0Utils02.getDataSource());
		//2调动qr的update
		qr.update(ConnectionManager.getConnection(),"delete from products where pid = ?", id);
	}
	
	//插入商品
	public void addProdcut(Product p) throws SQLException {
		// TODO Auto-generated method stub
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner(C3P0Utils02.getDataSource());
		//2.调用qr的update方法
		Object[] params = {p.getPname(),p.getPrice(),"1","c002"};
		qr.update("insert into products (pname,price,flag,category_id) values (?,?,?,?)", params);
	}

	public Product findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner(C3P0Utils02.getDataSource());
		//2.调用qr的query
		Product p = qr.query("select * from products where pid = ?",new BeanHandler<Product>(Product.class), id);
		return p;
	}

	public void updateProduct(Product p) throws SQLException {
		// TODO Auto-generated method stub
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner(C3P0Utils02.getDataSource());
		//2.修改
		qr.update("update products set pname=?,price=? where pid=?",p.getPname(),p.getPrice(),p.getPid());
	}

	public List<Product> findAll() throws SQLException {
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner(C3P0Utils02.getDataSource());
		//2.调用qr的query
		List<Product> ps = qr.query("select * from products", new BeanListHandler<Product>(Product.class));
		//返回
		return ps;
	}

}
