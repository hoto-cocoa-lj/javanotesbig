package cn.itcast.serviceimpl;

import cn.itcast.dao.TranFerDao;
import cn.itcast.service.TranFerService;

public class TranFerServiceImpl implements TranFerService
{
	private TranFerDao tranFerDao;
	public void setTranFerDao(TranFerDao tranFerDao) {
		this.tranFerDao = tranFerDao;
	}

	@Override
	public void tranfer(String toUser,String inUser,double money) 
	{
		// 减钱
		tranFerDao.toMoney(toUser,money);
		int i=1/0;
		// 加钱
		tranFerDao.inMoney(inUser,money);
		
	}

	
}
