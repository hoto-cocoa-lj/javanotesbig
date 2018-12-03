package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.mapper.OrdersMapper;
import cn.itcast.mybatis.mapper.userMapper;
import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.QueryVo;
import cn.itcast.mybatis.po.User;

public class JDBCTest2 {	
	private SqlSessionFactory s=null;
	@Before
	public void init() throws IOException{
		SqlSessionFactoryBuilder f = new SqlSessionFactoryBuilder();
		InputStream r = Resources.getResourceAsStream("sqlMapConfig.xml");
		s = f.build(r);	
	}
	
	@Test
	public void testfindUserById(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		User u = m.findUserById(10);
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testfindUserById2(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		Map u = m.findUserById2(10);
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testfindUserById3(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		Map u = m.findUserById3(1);
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testfindUserById4(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		User u = m.findUserById4(1);
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testfindUserById5(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		User u = m.findUserById5(1);
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testinsertUser(){	
		SqlSession se= s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		User u = new User();
		u.setUsername("李1");
		u.setBirthday(new Date());
		u.setSex("妖");
		u.setAddress("address");
		m.insertUser(u);
		se.commit();
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testinsertUser2(){	
		SqlSession se= s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		OrdersMapper m1 = se.getMapper(OrdersMapper.class);
		User u = new User();
		u.setUsername("test");
		u.setBirthday(new Date());
		u.setSex("妖");
		u.setAddress("test");
		int id = u.getId();
		List<Orders> orders=new ArrayList<Orders>();
		Orders o = new Orders();
		o.setUserId(id);
		o.setNote("onote");
		o.setNumber("0");
		o.setCreatetime(new Date());
		orders.add(o);
		u.setOrders(orders);
		
		m.insertUser(u);
		m1.insertOrders(o);
		se.commit();
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testfindUserByMap(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		HashMap map = new HashMap();
		map.put("username","三");
		map.put("sex",1);
		map.put("address","州");
		List<User> u=m.findUserByMap(map);
		System.out.println(u.size()+"\t"+u);
		se.close();
	}
	
	@Test
	public void testfindUserByMap1(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		HashMap map = new HashMap();
//		map.put("username","%");
//		map.put("sex",1);
		map.put("address","州");
		List<User> u=m.findUserByMap1(map);
		System.out.println(u.size()+"\t"+u);
		se.close();
	}
	
	@Test
	public void testfindUserByQueryVo(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		User u=new User();
		u.setUsername("三");
		u.setSex("1");
		u.setAddress("州");
		QueryVo queryVo = new QueryVo();
		queryVo.setUser(u);
		List<User> u1=m.findUserByQueryVo(queryVo);
		System.out.println(u1);
		se.close();
	}
	
	@Test
	public void testfindUserByQueryVoList1(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		int[] u={1,2,10};
		QueryVo queryVo = new QueryVo();
		queryVo.setInts(u);
		List<User> u1=m.findUserByQueryVoList1(queryVo);
		System.out.println(u1);
		se.close();
	}
	
	@Test
	public void testfindUserByQueryVoList2(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		int[] u={1,2,10};
		QueryVo queryVo = new QueryVo();
		queryVo.setInts(u);
		List<User> u1=m.findUserByQueryVoList2(queryVo);
		System.out.println(u1);
		se.close();
	}
	
	@Test
	public void testfindCount(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		int u1 = m.findCount();
		System.out.println(u1);
		se.close();
	}
	
	@Test
	public void testupdateUser(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		User u=new User();
		u.setId(31);
		u.setUsername("uu111u");
		m.updateUser(u);
		se.commit();
		se.close();
	}
	
	@Test
	public void testfindUserOrderList(){	
		SqlSession se = s.openSession();
		userMapper m = se.getMapper(userMapper.class);
		List<Map> l = m.findUserOrderList();
		System.out.println(l);
		se.close();
	}
	

}
