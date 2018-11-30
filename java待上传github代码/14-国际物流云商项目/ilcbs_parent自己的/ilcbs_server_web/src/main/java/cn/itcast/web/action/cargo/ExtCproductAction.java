package cn.itcast.web.action.cargo;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.ExtCproduct;
import cn.itcast.domain.Factory;
import cn.itcast.service.ExtCproductService;
import cn.itcast.service.FactoryService;
import cn.itcast.utils.Page;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Result(type="redirectAction",name ="action_list",
	location="extCproductAction_tocreate?contractProduct.id=${contractProduct.id}")
public class ExtCproductAction extends BaseAction<ExtCproduct> implements ModelDriven<ExtCproduct>{
	@Autowired
	private ExtCproductService ds;
	@Autowired
	private FactoryService fs;
	private ExtCproduct d=new ExtCproduct();

	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public ExtCproduct getModel() {
		// TODO Auto-generated method stub
		return d;
	}

	@Action(value="extCproductAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/cargo/contract/jExtCproductCreate.jsp")})	
	public String tocreate(){
		Specification<ExtCproduct> spec = new Specification<ExtCproduct>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<ExtCproduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return 	cb.equal(root.get("contractProduct").get("id").as(String.class),
																d.getContractProduct().getId());

			}
		};
		org.springframework.data.domain.Page<ExtCproduct> page2 = ds.
				findPage(spec, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("ExtCproduct_tocreate");
		super.push(page);
		Specification<Factory> spec1 = new Specification<Factory>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.notEqual(root.get("ctype").as(String.class),"货物");
			}
		};
		super.put("factoryList",fs.find(spec1));
		return "tocreate";
	}
	
	@Action(value="extCproductAction_insert")
	public String insert(){
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="extCproductAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/cargo/contract/jExtCproductUpdate.jsp")})	
	public String toupdate(){
		super.push(ds.get(d.getId()));
		Specification<Factory> spec1 = new Specification<Factory>() {
			@Override
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.notEqual(root.get("ctype").as(String.class),"货物");
			}
		};
		super.put("factoryList",fs.find(spec1));
		return "toupdate";
	}
	
	@Action(value="extCproductAction_update")
	public String update(){
		ExtCproduct d1=ds.get(d.getId());
		Factory fa = fs.get(d.getFactory().getId());
		d1.setFactory(fa);
//		d1.setFactoryName(fa.getFactoryName());	
		d1.setFactoryName(d.getFactoryName());		
		d1.setProductNo(d.getProductNo());
		d1.setProductImage(d.getProductImage());
		d1.setCnumber(d.getCnumber());
		d1.setPackingUnit(d.getPackingUnit());
		d1.setPrice(d.getPrice());
		d1.setOrderNo(d.getOrderNo());		
		d1.setProductDesc(d.getProductDesc());
		d1.setProductRequest(d.getProductRequest());
		ds.saveOrUpdate(d1);
		System.out.println(d1.getFactoryName()+"++++++++++++"+d.getFactoryName());
		return "action_list";
	}
	@Action(value="extCproductAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
		ds.delete(a.split(","));
		return "action_list";
	}
}
