package com.zz.view;

import com.zz.service.AccountService;

public class AccountView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String n1 = "b";
		String n2 = "c";
		double money = 2000;
		AccountService.transfer(n1, n2, money);

	}

}
