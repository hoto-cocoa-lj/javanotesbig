package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	static Configuration cfg = null;
	static SessionFactory sf = null;
	static {
		cfg = new Configuration();
		cfg.configure();
		sf = cfg.buildSessionFactory();
	}

	public static Session opens() {
		return sf.openSession();
	}

	// 需要在hibernate.cfg.xml设置： // <property
	// name="hibernate.current_session_context_class">thread</property>
	public static Session getcs() {
		return sf.getCurrentSession();
	}
}
