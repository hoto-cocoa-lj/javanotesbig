package cn.itcast.mybatis.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.QueryVo;
import cn.itcast.mybatis.po.User;

public interface userMapper	 {	

	public User findUserById(int id);
	
	public Map findUserById2(int id);
	
	public Map findUserById3(int id);
	
	public User findUserById4(int id);
	
	public User findUserById5(int id);
	
	public void insertUser(User u);
	
	public List<User> findUserByMap(Map m);
	
	public List<User> findUserByMap1(Map m);
	
	public List<User> findUserByQueryVo(QueryVo queryVo);
	
	public List<User> findUserByQueryVoList1(QueryVo queryVo);
	
	public List<User> findUserByQueryVoList2(QueryVo queryVo);
	
	public int findCount();
	
	public void updateUser(User u);
	
	public List<Map> findUserOrderList();

}
