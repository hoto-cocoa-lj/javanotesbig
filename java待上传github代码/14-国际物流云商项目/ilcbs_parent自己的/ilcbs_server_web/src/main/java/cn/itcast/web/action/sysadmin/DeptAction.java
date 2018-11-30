package cn.itcast.web.action.sysadmin;

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

import cn.itcast.domain.Dept;
import cn.itcast.service.DeptService;
import cn.itcast.utils.Page;
import cn.itcast.web.action.BaseAction;

@Namespace("/sysadmin")
@Result(type="redirectAction",name ="action_list",location="deptAction_list")
public class DeptAction extends BaseAction<Dept> implements ModelDriven<Dept>{
	@Autowired
	private DeptService ds;
	private Dept d=new Dept();
	private 		// deptList,0停用的就不要了
	Specification<Dept> spec = new Specification<Dept>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// Dept where state = 1
		@Override
		public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Dept getModel() {
		// TODO Auto-generated method stub
		return d;
	}
	
	@Action(value="deptAction_list",results={@Result(name="list",location=
			"/WEB-INF/pages/sysadmin/dept/jDeptList.jsp")})
	public String list() {
		org.springframework.data.domain.Page<Dept> page2 = ds.
				findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("deptAction_list");
		super.push(page);
		return "list";
	}
	
	@Action(value="deptAction_toview",results={@Result(name="toview",location=
			"/WEB-INF/pages/sysadmin/dept/jDeptView.jsp")})	
	public String toview(){
		d=ds.get(d.getId());
		super.push(d);
		return "toview";
	}
		
	@Action(value="deptAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/sysadmin/dept/jDeptCreate.jsp")})	
	public String tocreate(){
		List<Dept> deptList=ds.find(spec);
		super.put("deptList",deptList);
		return "tocreate";
	}
	
	@Action(value="deptAction_insert")
	public String insert(){
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="deptAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/sysadmin/dept/jDeptUpdate.jsp")})	
	public String toupdate(){
		d=ds.get(d.getId());
		super.push(d);
		List<Dept> deptList=ds.find(spec);
		deptList.remove(d);
		super.put("deptList",deptList);
		return "toupdate";
	}
	
	@Action(value="deptAction_update")
	public String update(){
		Dept d1=ds.get(d.getId());
		d1.setDeptName(d.getDeptName());
		d1.setParent(d.getParent());
		ds.saveOrUpdate(d1);
		return "action_list";
	}
	@Action(value="deptAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
		ds.delete(a.split(","));
//		for(String ss:a.split(",")){
//			ds.dg_delete(ss);
//		}
		return "action_list";
	}
}
