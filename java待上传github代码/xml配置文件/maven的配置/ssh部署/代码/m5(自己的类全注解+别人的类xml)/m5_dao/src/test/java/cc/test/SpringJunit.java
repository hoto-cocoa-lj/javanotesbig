package cc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import cc.dao.CustomerDao;

@ContextConfiguration(value = "classpath:applicationContext-dao.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {

	@Autowired
	private CustomerDao cd;

	@Test
	public void test1() {
		System.out.println(cd); // @6d1ef78d
cd.a();
	}


}
