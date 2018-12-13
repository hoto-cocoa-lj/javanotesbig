package cn.itcvast.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import  org.apache.solr.client.solrj.impl.BinaryResponseParser;
//he.solr.client.solrj.impl.HttpSolrServer.HttpSolrServer

@Repository
public class JDdao {
	@Autowired
	private SolrServer solrServer;
//	private String url="http://localhost:10080/solr/collection1";	
	public SolrDocumentList s1(SolrQuery sq) throws SolrServerException{
//		SolrServer solrServer=new HttpSolrServer(url);
//		sq.set("q", "*");
		sq.set("df","product_keywords");
		/*sq.set("q", "花儿朵朵");
		sq.set("fq","product_catalog_name:时尚卫浴");
		sq.set("fq","product_price:[0 TO 200]");
		sq.set("start","0");
		sq.set("rows","10");
		sq.set("df","product_keywords");*/
		sq.setHighlight(true);
		sq.setHighlightSimplePre("<b>");
		sq.setHighlightSimplePost("</b>");
		sq.addHighlightField("product_name");
		sq.set("rows",60);
//		sq.set("sort","product_price desc");
		
		QueryResponse q= solrServer.query(sq);
		SolrDocumentList l = q.getResults();
		System.out.println(l.size()+"\t"+l.getNumFound());
//		for(SolrDocument d:l){
//			System.out.println(d.get("id")+"\t"+d.get("product_name")
//			+"\t"+d.get("product_catalog_name")+"\t"+d.get("product_picture")
//			+"\t"+d.get("product_price")+"\t"+d.get("product_description")
//			+"\t"+d.get("product_keywords"));
//		}
		return l;
	}
	
	public List s2(SolrQuery sq) throws SolrServerException{
//		SolrServer solrServer=new HttpSolrServer(url);
//		sq.set("q", "*");
		sq.set("df","product_keywords");
		/*sq.set("q", "花儿朵朵");
		sq.set("fq","product_catalog_name:时尚卫浴");
		sq.set("fq","product_price:[0 TO 200]");
		sq.set("start","0");
		sq.set("rows","10");
		sq.set("df","product_keywords");*/
		sq.setHighlight(true);
		sq.setHighlightSimplePre("<b>");
		sq.setHighlightSimplePost("</b>");
		sq.addHighlightField("product_name");
		sq.set("rows",60);
//		sq.set("sort","product_price desc");
		
		QueryResponse q= solrServer.query(sq);
		SolrDocumentList l = q.getResults();
		System.out.println(l.size()+"\t"+l.getNumFound());		

		List al=new ArrayList();
		al.add(l);
		Map<String, Map<String, List<String>>> h = q.getHighlighting();
		al.add(h);
		return al;
	}
}
