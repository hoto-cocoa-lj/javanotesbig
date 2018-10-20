package com.itheima.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.itheima.utils.C3P0Utils02;
import com.itheima.utils.ConnectionManager;

/**
 * 转账DAO层,主要就是操作数据库
 * 
 * 我们案例中只涉及到修改操作
 * 
 * @author yingpeng
 *
 */
public class AccountDao {
	
	//转账出去
	//jack 转出去 1000元
	public void fromAccount(String fromName,double money) throws SQLException{
		Connection conn = ConnectionManager.getConnection();
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner();
		//2.执行减钱操作
		qr.update(conn,"update account set money=money-? where name=?", money,fromName);
	}
	
	//收钱回来
	public void toAccount(String toName,double money) throws SQLException{
		Connection conn = ConnectionManager.getConnection();
		//1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner();
		//2.加钱操作
		qr.update(conn,"update account set money=money+? where name=?",money,toName);

	}
	
}
