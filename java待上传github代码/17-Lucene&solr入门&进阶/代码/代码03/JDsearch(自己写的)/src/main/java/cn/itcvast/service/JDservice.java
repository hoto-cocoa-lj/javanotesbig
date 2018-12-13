package cn.itcvast.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;

public interface JDservice {
	public SolrDocumentList s1(SolrQuery sq) throws SolrServerException;
}
