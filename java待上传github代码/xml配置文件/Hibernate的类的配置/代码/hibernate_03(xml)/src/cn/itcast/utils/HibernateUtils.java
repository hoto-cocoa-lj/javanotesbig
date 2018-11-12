package cn.itcast.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils 
{
		static Configuration configuration =null;
		static SessionFactory sessionFactory = null;
		static
		{
			// 加载一次配置文件
			configuration = new Configuration();
			configuration.configure();
			
			// 获取一个sessionFactory
			sessionFactory=configuration.buildSessionFactory();
			
		}
		
		// 从连接池获取的
		public static Session openSession()
		{
			return sessionFactory.openSession();
		}
		
		// 从当前线程中获取绑定的session 
		// 好处: 在多层之间调用方法获取的都是同一个session
		public static Session getCurrentSession()
		{
			/*特点: 1 默认是关闭的 需要配置开启
				   2 会自动给你关闭连接*/
			Session session = sessionFactory.getCurrentSession();
			return session;
		}
}
