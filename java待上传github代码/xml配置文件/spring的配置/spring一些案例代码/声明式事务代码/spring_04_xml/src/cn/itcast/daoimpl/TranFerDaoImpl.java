package cn.itcast.daoimpl;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.itcast.dao.TranFerDao;

public class TranFerDaoImpl implements TranFerDao
{
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate =jdbcTemplate;
	}

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
