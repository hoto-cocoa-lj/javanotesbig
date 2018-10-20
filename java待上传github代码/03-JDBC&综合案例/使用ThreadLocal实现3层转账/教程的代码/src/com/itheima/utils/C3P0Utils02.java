package com.itheima.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 在C3P0连接池中 遵循了javax.sql.DataSource接口的实现类:
 * 		ComboPooledDataSource
 * 		
 * 
 * @author yingpeng
 *
 */
public class C3P0Utils02 {

	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	
	
	public static DataSource getDataSource(){
		return ds;
	}
	//static代码块设置数据库连接四大要素
	
	public static Connection getConnection() throws SQLException{
		//获取连接,不要自己去DriverManager获取,而是从C3P0连接池获取
		return ds.getConnection();
	}
	
	//关闭所有资源的统一代码
	public static void closeAll(Connection conn,Statement st,ResultSet rs){
		//负责关闭
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
