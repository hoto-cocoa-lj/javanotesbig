package cn.itcvast.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcvast.dao.JDdao;
import cn.itcvast.service.JDservice;

@Service
public class JDserviceImpl implements JDservice{
	@Autowired
	private JDdao jDdao;
	@Override
	public SolrDocumentList s1(SolrQuery sq) throws SolrServerException {
		return jDdao.s1(sq);
	}
	
}
