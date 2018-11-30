package cn.itcast.web.action.sysadmin;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import cn.itcast.domain.Role;
import cn.itcast.domain.Module;
import cn.itcast.service.DeptService;
import cn.itcast.service.RoleService;
import cn.itcast.service.ModuleService;
import cn.itcast.utils.Page;
import cn.itcast.web.action.BaseAction;


@Namespace("/sysadmin")
@Result(type="redirectAction",name ="action_list",location="moduleAction_list")
public class ModuleAction extends BaseAction<Module> implements ModelDriven<Module>{
	@Autowired
	private ModuleService ds;
	@Autowired
	private DeptService dsDept;
	@Autowired
	private RoleService rs;
	private Module d=new Module();
	private String  roleIds;
	
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	private 		// moduleList,0停用的就不要了
	Specification<Module> spec = new Specification<Module>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// Module where state = 1
		@Override
		public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			// TODO Auto-generated method stub
			return cb.equal(root.get("state").as(Integer.class), 1);
		}
	};
	Specification<Dept> specDept = new Specification<Dept>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// Module where state = 1
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
	public Module getModel() {
		// TODO Auto-generated method stub
		return d;
	}
	
	@Action(value="moduleAction_list",results={@Result(name="list",location=
			"/WEB-INF/pages/sysadmin/module/jModuleList.jsp")})
	public String list() {
		org.springframework.data.domain.Page<Module> page2 = ds.
				findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("moduleAction_list");
		super.push(page);
		return "list";
	}
	
	@Action(value="moduleAction_toview",results={@Result(name="toview",location=
			"/WEB-INF/pages/sysadmin/module/jModuleView.jsp")})	
	public String toview(){
		d=ds.get(d.getId());
		super.push(d);
		return "toview";
	}
		
	@Action(value="moduleAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/sysadmin/module/jModuleCreate.jsp")})	
	public String tocreate(){
		List<Dept> deptList=dsDept.find(specDept);
//		System.out.println("tocreate deptList="+deptList);
		super.put("deptList",deptList);
		List<Module> moduleList=ds.find(null);
		super.put("moduleList",moduleList);	
		return "tocreate";
	}
	
	@Action(value="moduleAction_insert")
	public String insert(){
		
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="moduleAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/sysadmin/module/jModuleUpdate.jsp")})	
	public String toupdate(){
		d=ds.get(d.getId());
		super.push(d);		
		List<Dept> deptList=dsDept.find(specDept);
		super.put("deptList",deptList);
		return "toupdate";
	}
	
	@Action(value="moduleAction_update")
	public String update(){
		System.out.println("update d="+d);
		Module d1=ds.get(d.getId());
		d1.setState(d.getState());
		d1.setName(d.getName());
		d1.setLayerNum(d.getLayerNum());
		d1.setCpermission(d.getCpermission());
		d1.setCurl(d.getCurl());
		d1.setCtype(d.getCtype());
		d1.setBelong(d.getBelong());
		d1.setCwhich(d.getCwhich());
		d1.setRemark(d.getRemark());
		d1.setOrderNo(d.getOrderNo());		
		
		ds.saveOrUpdate(d1);
		return "action_list";
	}
	@Action(value="moduleAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
		ds.delete(a.split(","));
//		for(String ss:a.split(",")){
//			ds.dg_delete(ss);
//		}
		return "action_list";
	}
	@Action(value="moduleAction_torole",results=@Result(name="torole",
			location="/WEB-INF/pages/sysadmin/module/jModuleRole.jsp"))
	public String torole(){	
		System.out.println("torole id="+d.getId());
		d=ds.get(d.getId());
		super.push(d);
		String roleStr="";
		for(Role r:d.getRoles()){
			roleStr=roleStr+r.getName()+",";
		}
		super.put("roleStr",roleStr);
		List<Role> roleList=rs.find(null);
		super.put("roleList",roleList);
		return "torole";
	}
	
	@Action(value="moduleAction_role")
	public String role(){
		roleIds=roleIds.replaceAll("\\s+","");
		Module d1=ds.get(d.getId());
		d1.getRoles().clear();
		for(String ri:roleIds.split(",")){
			d1.getRoles().add(rs.get(ri));
		}
		ds.saveOrUpdate(d1);
		return "action_list";
	}
}
