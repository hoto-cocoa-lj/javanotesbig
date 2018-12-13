package cn.itcast.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

public class SolrManager {
	/**
	 * 添加
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void addIndex() throws Exception, IOException{
//		1、获取solr的服务器 用于连接solr项目
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
//		2、执行solrServer的添加方法
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("name", "solrJ添加name");
		doc.addField("content", "solrJ添加的内容");
		doc.addField("id", "2");
		solrServer.add(doc);
		solrServer.commit();
	}
	/**
	 * 修改
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void updateIndex() throws Exception, IOException{
//		1、获取solr的服务器 用于连接solr项目
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
//		2、执行solrServer的添加方法
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("name", "solrJ添加name修改");
		doc.addField("content", "solrJ添加的内容修改");
		doc.addField("id", "2");
		solrServer.add(doc);
		solrServer.commit();
	}
	/**
	 * 删除
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void deleteIndex() throws Exception, IOException{
//		1、获取solr的服务器 用于连接solr项目
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
//		solrServer.deleteById("1");
//		solrServer.deleteByQuery("id:2");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	@Test
	public void searchIndex() throws Exception, IOException{
//		1、获取solr的服务器 用于连接solr项目
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
//		2、设置查询条件
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
//		3、执行
		QueryResponse queryResponse = solrServer.query(solrQuery);
//		4、获取结果
		SolrDocumentList results = queryResponse.getResults();
		System.out.println("总记录数:"+results.getNumFound());
		for (SolrDocument doc : results) {
			System.out.println(doc.get("id"));
			System.out.println(doc.get("name"));
			System.out.println(doc.get("content"));
		}
	}
	
	
}
