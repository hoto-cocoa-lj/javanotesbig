package com.itheima.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.itheima.dao.AccountDao;
import com.itheima.utils.C3P0Utils02;
import com.itheima.utils.ConnectionManager;

/**
 * 转账功能的业务层
 * 
 * @author yingpeng
 *
 */
public class AccountService {

	//转账业务
	public void transfer(String fromName,String toName,double money){
		//直接调用dao
		AccountDao dao = new AccountDao();
		try {
			//开启事务
			ConnectionManager.start();
			//转出去
			dao.fromAccount(fromName, money);
			//模拟异常
			System.out.println(1/0);
			//拿回来
			dao.toAccount(toName, money);
			//提交事务
			ConnectionManager.commit();
			System.out.println("转账成功!!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("程序出现异常,程序回滚");
			//回滚事务
			try {
				ConnectionManager.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				ConnectionManager.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
