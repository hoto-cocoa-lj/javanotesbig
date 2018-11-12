package cn.itcast.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.utils.HibernateUtils;

// 多对多的操作
public class Demo2
{
	@Test
	public void test1()
	{
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		// 保存2个用户 3个角色
		
		// 创建了2个用户
		User user1 = new User();      // 1
		user1.setUser_name("jack");
		User user2 = new User();     //  2
		user2.setUser_name("rose");
		
		// 创建3个角色
		Role role1= new Role();      // 1
		role1.setRole_name("员工");
		Role role2= new Role();     //  2
		role2.setRole_name("班主任");
		Role role3= new Role();    //  3
		role3.setRole_name("助教");
		
		// 让用户1关联角色
		user1.getRoles().add(role1);
		user1.getRoles().add(role2);
		// 让用户2关联角色
		user2.getRoles().add(role1);
		user2.getRoles().add(role3);
		
		// 让角色关联用户
		role1.getUsers().add(user1);
		role1.getUsers().add(user2);
		role2.getUsers().add(user1);
		role3.getUsers().add(user2);
		
		// 保存用户
		session.save(user1);  // 1-1
		session.save(user2);
		// 保存角色
		session.save(role1);  // 1-1
		session.save(role2);
		session.save(role3);

		tx.commit();
		session.close();
	}
	
	@Test //级联操作 
				// 保存用户的时候级联保存角色的数据
	public void test2()
	{
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		// 保存2个用户 3个角色
		
		// 创建了2个用户
		User user1 = new User();      // 1
		user1.setUser_name("jack");
		User user2 = new User();     //  2
		user2.setUser_name("rose");
		
		// 创建3个角色
		Role role1= new Role();      // 1
		role1.setRole_name("员工");
		Role role2= new Role();     //  2
		role2.setRole_name("班主任");
		Role role3= new Role();    //  3
		role3.setRole_name("助教");
		
		// 让用户1关联角色
		user1.getRoles().add(role1);
		user1.getRoles().add(role2);
		// 让用户2关联角色
		user2.getRoles().add(role1);
		user2.getRoles().add(role3);
		
		// 让角色关联用户
		role1.getUsers().add(user1);
		role1.getUsers().add(user2);
		role2.getUsers().add(user1);
		role3.getUsers().add(user2);
		
		// 保存用户
		session.save(user1);  // 1-1
		session.save(user2);
		// 保存角色
		/*session.save(role1);  
		session.save(role2);
		session.save(role3);*/

		tx.commit();
		session.close();
	}
	
	
	@Test  // 普通删除
	public void test3()
	{
		// 需求: 删除jack用户
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.get(User.class, 1L);
		session.delete(user);
		
		tx.commit();
		session.close();
		
	}
	
	
	@Test  // 级联删除
	public void test4()
	{
		// 需求: 级联删除rose用户
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.get(User.class, 2L);
		session.delete(user);
		
		tx.commit();
		session.close();
		
	}
	
	@Test  // 多对多的其它操作
			  // 给jack用户分配一个角色
			 // 给rose用户删除一个角色
			 // 给rose用户修改一个角色
	
	public void test5()
	{
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		//给jack用户分配一个角色
		
		// 1先获取用户jack
			User user = session.get(User.class, 1L);
		// 2获取要分配的角色助教
			Role role = session.get(Role.class, 2L);
		
		// 3 给jack添加分配的角色助教
			user.getRoles().add(role);
		
		
			tx.commit();
			session.close();
	}
	
	@Test
	public void test6()
	{
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		//给jack用户删除一个角色
		
		// 1先获取用户jack
			User user = session.get(User.class, 1L);
		// 2获取要删除的角色员工
			Role role = session.get(Role.class, 1L);
		
		// 3 给jack删除员工角色
			user.getRoles().remove(role);
		
		
			tx.commit();
			session.close();
	}
	
	
	@Test //给jack用户修改一个角色
	public void test7()
	{
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		// 1先获取用户jack
		User user = session.get(User.class, 1L);
		// 2在获取到助教角色
		Role role1 = session.get(Role.class, 2L);
		// 3在获取员工角色
		Role role2 = session.get(Role.class, 1L);
		
		// 4 先删除jack的助教角色 再添加员工角色
		user.getRoles().remove(role1);
		user.getRoles().add(role2);
		
		tx.commit();
		session.close();
	}
	
}
