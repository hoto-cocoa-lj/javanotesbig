package com.itheima.view;

import com.itheima.service.AccountService;

public class AccountView {
	public static void main(String[] args) {
		//模拟用户转账,输入付款人的姓名,收款人的姓名,转账金额
		String fromName = "jack";
		String toName = "tom";
		double money = 5000;
		
		AccountService service = new AccountService();
		
		service.transfer(fromName, toName, money);
	}
}
