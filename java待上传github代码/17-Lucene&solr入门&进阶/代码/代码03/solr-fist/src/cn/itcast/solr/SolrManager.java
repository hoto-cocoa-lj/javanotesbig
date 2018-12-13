package cn.itcast.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
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
	@Test
	public void searchIndex() throws Exception, IOException{
//		1、获取solr的服务器 用于连接solr项目
		SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/collection1");
		SolrQuery  solrQuery = new SolrQuery();
//        "q": "花儿朵朵",
		solrQuery.setQuery("花儿朵朵");//设置主查询条件
//		solrQuery.set("q","花儿朵朵");//设置主查询条件
		
//		"fq": [
//        "product_catalog_name:时尚卫浴",
//        "product_price:[0 TO 200]"
//       ]
		solrQuery.set("fq", "product_catalog_name:时尚卫浴");//设置过滤条件
		solrQuery.set("fq", "product_price:[0 TO 200]");//设置过滤条件
		
//      "start": "0",
//        "rows": "10",
		solrQuery.setStart(0);//设置起始位置
		solrQuery.setRows(10);//设置条数
//        "df": "product_keywords",
		solrQuery.set("df",  "product_keywords");//设置默认查询的域名

//        "hl": "true",
//	      "hl.simple.pre": "<span color="red">",
//        "hl.simple.post": "</span>"
//	      "hl.fl": "product_name",
		solrQuery.setHighlight(true);//开启高亮
		solrQuery.setHighlightSimplePre("<span color=\"red\">");
		solrQuery.setHighlightSimplePost("</span>");
		solrQuery.addHighlightField("product_name");

//        "sort": "product_price desc",
		solrQuery.setSort("product_price", ORDER.asc);//设置排序
		
//		执行查询
		QueryResponse queryResponse = solrServer.query(solrQuery);
		
		SolrDocumentList results = queryResponse.getResults();
		System.out.println("总条数："+results.getNumFound());
//		 <field column="pid" name="id"/> 
//		 <field column="name" name="product_name"/> 
//		 <field column="catalog_name" name="product_catalog_name"/> 
//		 <field column="price" name="product_price"/> 
//		 <field column="description" name="product_description"/> 
//		 <field column="picture" name="product_picture"/> 
		
		
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("product_name"));
			System.out.println(solrDocument.get("product_catalog_name"));
			System.out.println(solrDocument.get("product_price"));
			System.out.println(solrDocument.get("product_description"));
			System.out.println(solrDocument.get("product_picture"));
			System.out.println("-----------------------------");
		}
		
		
	}
	
	
}
