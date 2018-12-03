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
import org.apache.logging.log4j.core.config.Order;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.OrderUser;
import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.QueryVo;
import cn.itcast.mybatis.po.User;

public interface OrdersMapper	 {	

	public List<OrderUser> findOrderUserList();
	public List<Orders> findOrderList();
	public List<Orders> findOrderList1();
	public void insertOrders(Orders o);
	
}
