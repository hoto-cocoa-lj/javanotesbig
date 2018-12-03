package cn.itcast.mybatis.test;

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
import cn.itcast.mybatis.mapper.UserMapper;
import cn.itcast.mybatis.pojo.Orders;
import cn.itcast.mybatis.pojo.OrdersUser;
import cn.itcast.mybatis.pojo.QueryVo;
import cn.itcast.mybatis.pojo.User;

public class OrdersMapperTest {

	SqlSessionFactory sqlSessionFactory = null;
    @Before
	public void init() throws Exception{
    	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    	sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
	}
    
    @Test
    public void testFindOrdersUserList(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
    	List<OrdersUser> ordersUserList = mapper.findOrdersUserList();
    	System.out.println(ordersUserList);
    	sqlSession.close();
    }
    @Test
    public void testFindOrdersList(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
    	List<Orders> ordersList = mapper.findOrdersList();
    	System.out.println(ordersList);
    	sqlSession.close();
    }
    
    @Test
    public void testFindUserList(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
    	List<User> userList = mapper.findUserList();
    	System.out.println(userList);
    	sqlSession.close();
    }
    
}
