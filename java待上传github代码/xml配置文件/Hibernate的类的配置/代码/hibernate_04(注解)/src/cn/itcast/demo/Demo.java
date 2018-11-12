package cn.itcast.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import cn.itcast.domain.Customer;

public class Demo
{
	@Test  //保存
	public void t1()
	{
		// 加载数据库信息  sessionFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		// 获取连接  session
		EntityManager em = factory.createEntityManager();
		// 开启事务
		EntityTransaction tx = em.getTransaction(); //先获取事务
		tx.begin(); //开启事务
		
		Customer customer = new Customer();
		customer.setCust_name("jack");
		// 相当于save()
		em.persist(customer);
		
		tx.commit();
		em.close();
	
	}
	
	// 查询
	@Test
	public void t2()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// get()方法 立即加载
		/*Customer customer = em.find(Customer.class,1L);
		System.out.println(customer.getCust_name());*/
		// load()方法 延迟加载
		Customer customer = em.getReference(Customer.class, 1L);
		System.out.println(customer.getCust_name());
		tx.commit();
		em.close();
	}
	
	@Test //修改
		  // 测试jpa中的一级缓存 完全可以使用
	public void t3()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// 先查后改
		Customer customer = em.find(Customer.class, 1L);
		System.out.println(customer.getCust_name());
		customer.setCust_name("rose1111");
		
		// update()
		/*em.merge(customer);*/
		tx.commit();
		em.close();
	}

	@Test
	public void  t4()
	{
		// 加载持久化单元
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		// 获取连接
		EntityManager em = factory.createEntityManager();
		// 开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// 删除  先查后删
		Customer customer = em.find(Customer.class, 1L);
		// delete()
		em.remove(customer);
		// 提交
		tx.commit();
		// 关闭资源
		em.close();
	}
}
