package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.mybatis.mapper.OrdersMapper;
import cn.itcast.mybatis.mapper.userMapper;
import cn.itcast.mybatis.po.OrderUser;
import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.QueryVo;
import cn.itcast.mybatis.po.User;

public class JDBCTest3 {	
	@Autowired
	private SqlSessionFactory s;;

	@Test
	public void testfindOrderUserList(){	
		SqlSession se = s.openSession();
		OrdersMapper m = se.getMapper(OrdersMapper.class);
		List<OrderUser> u = m.findOrderUserList();
		System.out.println(u);
		se.close();
	}
	
	@Test
	public void testfindOrderList(){	
		SqlSession se = s.openSession();
		OrdersMapper m = se.getMapper(OrdersMapper.class);
		List<Orders> u = m.findOrderList();
		System.out.println(u.size()+"\t"+u);
		se.close();
	}
	
	@Test
	public void testfindOrderList1(){	
		SqlSession se = s.openSession();
		OrdersMapper m = se.getMapper(OrdersMapper.class);
		List<Orders> u = m.findOrderList1();
		System.out.println(u.size()+"\t"+u);
		se.close();
	}	
	

}
