package cn.itcast.serviceImpl;

import cn.itcast.dao.AccountDao;
import cn.itcast.service.AccountService;

public class AccountServiceImpl implements AccountService
{
	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void save() {
		accountDao.save();
		
	}

	@Override
	public void delete() {
		accountDao.delete();
		
	}

	@Override
	public void update() {
		accountDao.update();
		
	}

	@Override
	public void findAll() {
		accountDao.findAll();
		
	}

	@Override
	public void findByname() {
		accountDao.findByname();
		
	}

	@Override
	public void findCount() {
		accountDao.findCount();
		
	}

}
