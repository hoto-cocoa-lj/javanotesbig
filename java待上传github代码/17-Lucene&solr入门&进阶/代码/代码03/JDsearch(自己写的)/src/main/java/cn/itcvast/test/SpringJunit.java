package cn.itcvast.test;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.itcvast.dao.JDdao;
import cn.itcvast.service.JDservice;
import cn.itcvast.web.JDweb;

@ContextConfiguration(value = "classpath:applicationContext.xml")

@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	@Autowired
	private JDdao jDdao;
	@Autowired
	private JDservice jDserviceImpl;
	@Autowired
	private JDweb jDweb;
	
	
	@Test
	public void t3() throws SolrServerException{
		jDweb.t1();
	}
	@Test
	public void t1() throws SolrServerException{
		jDdao.s1();
	}
	@Test
	public void t2() throws SolrServerException{
		jDserviceImpl.t1();
	}
}
