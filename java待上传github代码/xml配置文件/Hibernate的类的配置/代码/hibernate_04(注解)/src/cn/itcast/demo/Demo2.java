package cn.itcast.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JPAUtils;

public class Demo2 
{
	// 全查
		// JPA方式: 类似query的方式
	@Test
	public void test1()
	{
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// 全查  from 类名
		Query qr = em.createQuery("from Customer");
		List<Customer> list = qr.getResultList(); // 之前的list()
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
		em.close();
	}
	
	// 条件查
	@Test
	public void test2()
	{
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// 条件查  from 类 where 属性名
		Query qr = em.createQuery("from Customer where cust_name like ?");
		qr.setParameter(1, "b%"); // hibernate对于占位符?是从0开始 JPA是从1开始
		List<Customer> list = qr.getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
		em.close();
	}
	
	
	// 聚合查
	@Test
	public void test3()
	{
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query qr = em.createQuery("select count(*) from Customer");
		Object obj = qr.getSingleResult(); // uniqueResult()
		System.out.println(obj);
		tx.commit();
		em.close();
	}
	
	
	
	
	
	@Test
	public void test()
	{
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// 分页查
		Query qr = em.createQuery("from Customer");
		qr.setFirstResult(1);
		qr.setMaxResults(3);
		List<Customer> list = qr.getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
		em.close();
	}
	
}
