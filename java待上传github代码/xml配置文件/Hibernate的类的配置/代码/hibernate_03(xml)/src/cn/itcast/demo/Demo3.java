package cn.itcast.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcast.domain.Customer;
import cn.itcast.domain.Linkman;
import cn.itcast.utils.HibernateUtils;

// 对象导航查询: 在hibernate玩查询 会把对象的数据查出来 还会看对象中是否有集合或别的对象
// 			     如果有一起全部查询出来
public class Demo3 
{
	@Test
	public void test1()
	{
		// 1  查询客户下面所有的联系人数量
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer customer = session.get(Customer.class, 1L);
		System.out.println(customer.getLinkmans().size());
		tx.commit();
		session.close();
		
	}
	
	
	@Test
	public void test2()
	{
		//2 查询当前联系人的所属客户名字?
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		Linkman linkman = session.get(Linkman.class, 1L);
		System.out.println(linkman.getCustomer().getCust_name());
		tx.commit();
		session.close();
	}
}
