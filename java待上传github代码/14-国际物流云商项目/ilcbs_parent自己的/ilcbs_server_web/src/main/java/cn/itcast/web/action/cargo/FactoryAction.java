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

import cn.itcast.domain.Factory;
import cn.itcast.service.FactoryService;
import cn.itcast.utils.Page;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Result(type="redirectAction",name ="action_list",location="factoryAction_list")
public class FactoryAction extends BaseAction<Factory> implements ModelDriven<Factory>{
	@Autowired
	private FactoryService ds;
	private Factory d=new Factory();
	private 		// factoryList,0停用的就不要了
	Specification<Factory> spec = new Specification<Factory>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// Factory where state = 1
		@Override
		public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			// TODO Auto-generated method stub
			return cb.equal(root.get("state").as(Integer.class), 1);
		}
	};


	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public Factory getModel() {
		// TODO Auto-generated method stub
		return d;
	}
	
	@Action(value="factoryAction_list",results={@Result(name="list",location=
			"/WEB-INF/pages/sysadmin/jFactoryList.jsp")})
	public String list() {
		org.springframework.data.domain.Page<Factory> page2 = ds.
				findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("factoryAction_list");
		super.push(page);
		return "list";
	}
	
	@Action(value="factoryAction_toview",results={@Result(name="toview",location=
			"/WEB-INF/pages/sysadmin/jFactoryView.jsp")})	
	public String toview(){
		d=ds.get(d.getId());
		super.push(d);
		return "toview";
	}
		
	@Action(value="factoryAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/sysadmin/jFactoryCreate.jsp")})	
	public String tocreate(){
		List<Factory> factoryList=ds.find(spec);
		super.put("factoryList",factoryList);
		return "tocreate";
	}
	
	@Action(value="factoryAction_insert")
	public String insert(){
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="factoryAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/sysadmin/jFactoryUpdate.jsp")})	
	public String toupdate(){
		d=ds.get(d.getId());
		super.push(d);
		List<Factory> factoryList=ds.find(spec);
		factoryList.remove(d);
		super.put("factoryList",factoryList);
		return "toupdate";
	}
	
	@Action(value="factoryAction_update")
	public String update(){
		Factory d1=ds.get(d.getId());

		ds.saveOrUpdate(d1);
		return "action_list";
	}
	@Action(value="factoryAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
		ds.delete(a.split(","));
//		for(String ss:a.split(",")){
//			ds.dg_delete(ss);
//		}
		return "action_list";
	}
}
