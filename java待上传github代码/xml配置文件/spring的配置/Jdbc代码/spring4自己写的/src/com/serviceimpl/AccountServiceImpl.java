package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dao.AccountDao;
import com.service.AccountService;

@Component("asi")
public class AccountServiceImpl implements AccountService {
	@Autowired
	@Qualifier(value = "adi")
	private AccountDao ad;

	@Override
	public void t1() {
		ad.t1();

	}

	@Override
	public void t2() {
		ad.t2();

	}

	@Override
	public void t3() {
		ad.t3();

	}

	@Override
	public void t4() {
		ad.t4();

	}

}
