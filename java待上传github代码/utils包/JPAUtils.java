package com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	public static EntityManagerFactory fac;
	static {
		fac = Persistence.createEntityManagerFactory("mysql");
	}

	public static EntityManager gete() {
		return fac.createEntityManager();
	}
}
