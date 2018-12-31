package com.pyg.solr.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import com.pyg.mapper.TbItemMapper;
import com.pyg.pojo.TbItem;
import com.pyg.pojo.TbItemExample;

@Component
public class SolrUtils {
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private SolrTemplate solrTemplate;
	
	
	public void importData(){
		TbItemExample e=new TbItemExample();
		e.createCriteria().andStatusEqualTo("1");
		List<TbItem> l = tbItemMapper.selectByExample(e);
		System.out.println(l.size()+"\t"+l);
		solrTemplate.saveBeans(l);
		solrTemplate.commit();
	}
	public static void main(String[] args) {
		String s="classpath*:*.xml";
		ApplicationContext app=new ClassPathXmlApplicationContext(s);
		SolrUtils so=app.getBean(SolrUtils.class);
		so.importData();
//		new SolrUtils().importData();
	}
}



applcationContext-solr.xml配置如下：

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:solr="http://www.springframework.org/schema/data/solr"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/data/solr
http://www.springframework.org/schema/data/solr/spring-solr-1.0.xsd
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
 http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd">


	<context:component-scan base-package="com.pyg.solr.utils"></context:component-scan>
		
	<!-- solr 服务器地址 -->
	<solr:solr-server id="solrServer" url="http://192.168.153.129:8081/solr/item" />
	<!-- solr 模板，使用 solr 模板可对索引库进行 CRUD 的操作 -->
	<bean id="solrTemplate" class="org.springframework.data.solr.core.SolrTemplate">
		<constructor-arg ref="solrServer" />
	</bean>
</beans>