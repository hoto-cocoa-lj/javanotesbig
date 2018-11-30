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
import cn.itcast.domain.Factory;
import cn.itcast.service.ContractProductService;
import cn.itcast.service.FactoryService;
import cn.itcast.utils.Page;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Result(type="redirectAction",name ="action_list",location="contractProductAction_tocreate?contract.id=${contract.id}")
public class ContractProductAction extends BaseAction<ContractProduct> implements ModelDriven<ContractProduct>{
	@Autowired
	private ContractProductService ds;
	@Autowired
	private FactoryService fs;
	private ContractProduct d=new ContractProduct();

	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public ContractProduct getModel() {
		// TODO Auto-generated method stub
		return d;
	}
		
	@Action(value="contractProductAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/cargo/contract/jContractProductCreate.jsp")})	
	public String tocreate(){
		Specification<ContractProduct> spec = new Specification<ContractProduct>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<ContractProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("contract").as(Contract.class),d.getContract());
				//return cb.equal(root.get("contract").get("id").as(String.class),d.getContract().getId());
			}
		};
		org.springframework.data.domain.Page<ContractProduct> page2 = ds.
				findPage(spec, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("contractProductAction_tocreate");
		super.push(page);
		Specification<Factory> spec1 = new Specification<Factory>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("ctype").as(String.class),"货物");
			}
		};
		super.put("factoryList",fs.find(spec1));
		return "tocreate";
	}
	
	@Action(value="contractProductAction_insert")
	public String insert(){
		ds.saveOrUpdate(d);
//		System.out.println(d.getFactory().getId()+"xxx"+d.getContract().getId());
		return "action_list";
	}
	
	@Action(value="contractProductAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/cargo/contract/jContractProductUpdate.jsp")})	
	public String toupdate(){
		super.push(ds.get(d.getId()));
		Specification<Factory> spec1 = new Specification<Factory>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("ctype").as(String.class),"货物");
			}
		};
		super.put("factoryList",fs.find(spec1));
		
		return "toupdate";
	}
	
	@Action(value="contractProductAction_update")	
	public String update(){
		ContractProduct d1 = ds.get(d.getId());
		d1.setFactory(fs.get(d.getFactory().getId()));
		d1.setFactoryName(d.getFactoryName());
		d1.setProductNo(d.getProductNo());
		d1.setProductImage(d.getProductImage());
		d1.setCnumber(d.getCnumber());
		d1.setPackingUnit(d.getPackingUnit());
		d1.setLoadingRate(d.getLoadingRate());
		d1.setBoxNum(d.getBoxNum());
		d1.setPrice(d.getPrice());
		d1.setOrderNo(d.getOrderNo());
		d1.setProductDesc(d.getProductDesc());
		d1.setProductRequest(d.getProductRequest());
		ds.saveOrUpdate(d1);
		return "action_list";
	}
	
	@Action(value="contractProductAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
		ds.delete(a.split(","));
//		for(String ss:a.split(",")){
//			ds.dg_delete(ss);
//		}
		return "action_list";
	}
}
