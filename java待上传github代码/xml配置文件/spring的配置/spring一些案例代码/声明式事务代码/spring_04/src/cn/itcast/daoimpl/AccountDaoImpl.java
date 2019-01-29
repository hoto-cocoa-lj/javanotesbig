package cn.itcast.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao
{
	/*继承DaoSupport
	 * 
	 *  1 在appliactionContext.xml文件中 使用set方式注入了  但是AccountDaoImpl没有set方法
	 *  2 如果自己里面没有找到 要去父类里面找 setJdbcTempalte() 找到了 注入给父类的jdbcTempalte
	 *  3 父类如果有了 自己子类为什么不能用jdbcTempalte 因为父类private私有化了
	 *  4 但是父类里面有一个getJdbcTempalte 相当于子类里面也有一个 
	 *  5 就可以在子类中使用getJdbcTempalte的方法 获取到注入好的JdbcTempalte
	 * */
	

	@Override
	public void save() {
		String sql="insert into account values(?,?)";
		getJdbcTemplate().update(sql,"Tom",100000);
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findAll() {
		String sql="select * from account";
		List<Account> list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Account.class));
		for (Account account : list) {
			System.out.println(account);
		}
		
	}

	@Override
	public void findByname() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
	/*
	 * 
	 * set方式注入--第一种
	 * 
	 * private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save() {
		
		String sql="insert into account values(?,?)";
		jdbcTemplate.update(sql,"Tom",100000);
	}

	@Override
	public void delete() {
		String sql="delete from account where username=?";
		jdbcTemplate.update(sql,"Tom");
		
	}

	@Override
	public void update() {
		String sql="update account set money=? where username=?";
		jdbcTemplate.update(sql, 999,"rose");
		
	}
	@Override // 自己做实现
	public void findAll() {
		String sql="select * from account";
		List<Account> list = jdbcTemplate.query(sql, new RowMapper<Account>(){
			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				// 自己实现
				Account account = new Account();
				account.setUsername(rs.getString("username"));
				account.setMoney(rs.getDouble("money"));
				return account;
			}});
		
		for (Account account : list) {
			System.out.println(account);
		}
	}

	@Override
	public void findAll()
	{
		String sql="select * from account";
		List<Account> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Account.class));
		for (Account account : list) {
			System.out.println(account);
		}
	}

	@Override
	public void findByname() {
		String sql="select * from account where username=?";
		Account account= jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(Account.class),"jack");
		System.out.println(account);
	}

	@Override
	public void findCount() {
		String sql="select count(*) from account";
		Long l = jdbcTemplate.queryForObject(sql, long.class);
		System.out.println(l);
	}*/
}
