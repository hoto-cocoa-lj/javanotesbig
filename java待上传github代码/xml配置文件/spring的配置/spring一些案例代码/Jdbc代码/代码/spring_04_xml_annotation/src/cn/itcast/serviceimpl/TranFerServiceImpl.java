package cn.itcast.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.TranFerDao;
import cn.itcast.service.TranFerService;

@Service("tranFerService")
@Transactional
public class TranFerServiceImpl implements TranFerService
{
	@Autowired
	private TranFerDao tranFerDao;
	
	public void tranfer(String toUser,String inUser,double money) 
	{
		// 减钱
		tranFerDao.toMoney(toUser,money);
		int i=1/0;
		// 加钱
		tranFerDao.inMoney(inUser,money);
		
	}

	
}
