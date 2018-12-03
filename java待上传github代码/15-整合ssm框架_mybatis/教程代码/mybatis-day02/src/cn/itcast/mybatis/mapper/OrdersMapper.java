package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.pojo.Orders;
import cn.itcast.mybatis.pojo.OrdersUser;
import cn.itcast.mybatis.pojo.User;

public interface OrdersMapper {

	public List<OrdersUser> findOrdersUserList();
	
	public List<Orders> findOrdersList();
	
	
	public List<User> findUserList();
}
