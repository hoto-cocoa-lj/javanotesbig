package com.zz.service;

import java.sql.SQLException;

import com.zz.dao.AccountDao;
import com.zz.utils.ConnectionManager;

public class AccountService {

	public static void main(String[] args) {
	}

	// 查询余额
	public static void transfer(String n1, String n2, double money) {
		try {
			ConnectionManager.getConnection();
			ConnectionManager.start();
			AccountDao.cAccount(n1, money);
			// System.out.println(1 / 0);
			AccountDao.cAccount(n2, money * -1);
			ConnectionManager.commit();
			System.out.println("转账成功");
		} catch (Exception e) {
			try {
				e.printStackTrace();
				ConnectionManager.rollback();
				System.out.println("转账失败，回滚成功....");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("转账失败，回滚成功....");
			} finally {
				try {
					ConnectionManager.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
