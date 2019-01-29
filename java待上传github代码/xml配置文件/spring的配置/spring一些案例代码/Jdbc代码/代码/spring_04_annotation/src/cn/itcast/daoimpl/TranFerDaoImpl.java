package cn.itcast.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.TranFerDao;

@Repository("tranFerDao")
public class TranFerDaoImpl implements TranFerDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public void toMoney(String toUser, double money)
	{
		String sql="update account set money=money-? where username=?";
		jdbcTemplate.update(sql, money,toUser);
		
	}

	@Override
	public void inMoney(String inUser, double money) {
		String sql="update account set money=money+? where username=?";
		jdbcTemplate.update(sql, money,inUser);
	}

}
