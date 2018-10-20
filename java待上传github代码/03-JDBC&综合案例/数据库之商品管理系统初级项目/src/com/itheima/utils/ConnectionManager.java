package com.itheima.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接管理类:
 * 	主要负责获取连接,开启事务,提交事务,回滚事务
 * @author yingpeng
 *
 */
public class ConnectionManager {
	//1.定义一个集合 ThreadLocal 对象来保存当前线程的连接
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//2.获取连接
	public static Connection getConnection() throws SQLException{
		//1.先从tl中获取连接
		Connection conn =  tl.get();
		//2.判断conn是否为空
		if(conn == null){
			//说明 是service层第一次获取
			conn = C3P0Utils02.getConnection();
			tl.set(conn);
		}
		//如果不为空 说明 是dao层第二次以后获取
		return conn;
	}
	
	//3.开启事务
	public static  void start() throws SQLException{
		ConnectionManager.getConnection().setAutoCommit(false);
	}
	
	//4.提交事务
	public static void commit() throws SQLException{
		ConnectionManager.getConnection().commit();
	}
	//5.回滚事务
	public static void rollback() throws SQLException{
		ConnectionManager.getConnection().rollback();
	}
	//6.关闭连接
	public static void close() throws SQLException{
		ConnectionManager.getConnection().close();
	}
}
