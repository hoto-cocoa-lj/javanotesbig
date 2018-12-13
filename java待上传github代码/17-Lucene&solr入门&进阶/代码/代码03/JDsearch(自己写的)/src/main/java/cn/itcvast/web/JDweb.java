package cn.itcvast.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcvast.dao.JDdao;
import cn.itcvast.domain.Product;
import cn.itcvast.domain.QueryVo;
import cn.itcvast.service.JDservice;
import cn.itcvast.service.impl.JDserviceImpl;

@Controller
public class JDweb {

	@Autowired
	private JDdao jDdao;
	
	@Autowired
	private JDservice jDserviceImpl;

	@RequestMapping(value="/list")
	public String list(String queryString,String catalog_name,String price,
		String sort,String	page,QueryVo q,Model model) throws SolrServerException{
		if(page==null || page.trim()==""|| page.trim()=="0"){page="1";}
		
		QueryVo queryVo=new QueryVo();
		SolrQuery sq=new SolrQuery();
		
		
		//
		if(queryString==null || queryString.trim()==""){
			queryString="*";
		}
		sq.set("q", queryString);	
		
		//页面处理
		System.out.println("page="+page);
		int pp = 60;
		sq.set("start",(Integer.parseInt(page)-1)*pp);
		
		//排序设置	
		if("0".equals(sort)){
			sq.set("sort","product_price asc");
//			sq.setSort(SortClause.asc("product_price"));
		}else if("1".equals(sort)){
			sq.set("sort","product_price desc");
//			sq.setSort(SortClause.asc("product_price"));
		}
		
		//商品类别
		if(catalog_name!=null && catalog_name.trim()!=""){		
//			sq.set("fq","product_catalog_name:"+catalog_name);
			sq.addFilterQuery("product_catalog_name:"+catalog_name);
		}		
		
		//价格区间
		if(price!=null && price.trim()!=""){
			if(price.indexOf("以上")==-1){				
				String[] a=price.toString().split("-");
				System.out.println("数组a="+Arrays.toString(a));
//				sq.set("fq","product_price:["+a[0]+" TO "+a[1]+"]");
				sq.addFilterQuery("product_price:["+a[0]+" TO "+a[1]+"]");
			}else{
				String a=price.replace("以上","");
//				sq.set("fq","product_price:["+a+" TO *]");
				sq.addFilterQuery("product_price:["+a+" TO *]");
			}
		}
		
		
		System.out.println(sq+"\tsortv="+sort+"\tcatalog_namev="+catalog_name
				+"\tpricev="+price+"\tpagev="+page);
		
		List s2 = jDdao.s2(sq);
		SolrDocumentList l = (SolrDocumentList) s2.get(0);
		Map<String, Map<String, List<String>>> h=
				(Map<String, Map<String, List<String>>>) s2.get(1);
		List<Product> l2=new ArrayList<Product>();
		for(SolrDocument a:l){
			Product p=new Product();
			p.setPid(Long.parseLong(a.get("id").toString()));
			String z="";
			try{
				z=h.get(a.get("id").toString()).get("product_name").get(0);
			}catch(Exception e){
				System.out.println("没有在name");
			}
			if(z==""){
				z=a.get("product_name").toString();
			}
			p.setName(z);
			p.setPicture(a.get("product_picture").toString());
			p.setPrice(Double.parseDouble(a.get("product_price").toString()));
			l2.add(p);
		}
		queryVo.setProductList(l2);
		queryVo.setCurPage(Integer.parseInt(page));
		queryVo.setRecordCount(l.getNumFound());
/*		double pc1 = (l.getNumFound()*1.0/60);
		Integer pageCount1=Integer.parseInt(Math.ceil(pp)+"");*/
		Double pc = Math.ceil((l.getNumFound()*1.0/60));
		Integer pageCount=pc.intValue();
		queryVo.setPageCount(pageCount);
		
		model.addAttribute("result",queryVo);
		model.addAttribute("sort", sort);
		model.addAttribute("queryString", queryString);
		model.addAttribute("price", price);
		model.addAttribute("catalog_name", catalog_name);
		return "product_list";
	}
	
	public void t1() throws SolrServerException{

	}
}
