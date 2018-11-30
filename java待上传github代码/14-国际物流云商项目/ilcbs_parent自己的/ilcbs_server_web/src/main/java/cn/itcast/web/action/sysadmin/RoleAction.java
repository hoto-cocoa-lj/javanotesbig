package cn.itcast.web.action.sysadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Dept;
import cn.itcast.domain.Module;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.exception.SysException;
import cn.itcast.service.DeptService;
import cn.itcast.service.ModuleService;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import cn.itcast.utils.Page;
import cn.itcast.web.action.BaseAction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
@Namespace("/sysadmin")
@Result(type="redirectAction",name ="action_list",location="roleAction_list")
public class RoleAction extends BaseAction<Role> implements ModelDriven<Role>{
	@Autowired
	private RoleService ds;
	@Autowired
	private RedisTemplate r;
	@Autowired
	private JedisPool pool;
	@Autowired
	private DeptService dsDept;
	@Autowired
	private RoleService rs;
	@Autowired
	private ModuleService ms;
	private Role d=new Role();
	private String moduleIds;

	public void setModuleIds(String moduleIds) {
	this.moduleIds = moduleIds;
}

	private 		// roleList,0停用的就不要了
	Specification<User> spec = new Specification<User>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// User where state = 1
		@Override
		public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			// TODO Auto-generated method stub
			return cb.equal(root.get("state").as(Integer.class), 1);
		}
	};
	Specification<Dept> specDept = new Specification<Dept>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// User where state = 1
		@Override
		public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			// TODO Auto-generated method stub
			return cb.equal(root.get("state").as(Integer.class), 1);
		}
	};
	Specification<Module> specModule = new Specification<Module>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// User where state = 1
		@Override
		public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Role getModel() {
		// TODO Auto-generated method stub
		return d;
	}
	
	@Action(value="roleAction_list",results={@Result(name="list",location=
			"/WEB-INF/pages/sysadmin/role/jRoleList.jsp")})
	public String list() {
		org.springframework.data.domain.Page<Role> page2 = ds.
				findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("roleAction_list");
		super.push(page);
		return "list";
	}
	
	@Action(value="roleAction_toview",results={@Result(name="toview",location=
			"/WEB-INF/pages/sysadmin/role/jRoleView.jsp")})	
	public String toview(){
		d=ds.get(d.getId());
		super.push(d);
		return "toview";
	}
		
	@Action(value="roleAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/sysadmin/role/jRoleCreate.jsp")})	
	public String tocreate(){
		List<Dept> deptList=dsDept.find(specDept);
//		System.out.println("tocreate deptList="+deptList);
		super.put("deptList",deptList);
		List<Role> roleList=ds.find(null);
		super.put("roleList",roleList);	
		return "tocreate";
	}
	
	@Action(value="roleAction_insert")
	public String insert(){
		
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="roleAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/sysadmin/role/jRoleUpdate.jsp")})	
	public String toupdate(){
		d=ds.get(d.getId());
		super.push(d);		
		List<Dept> deptList=dsDept.find(specDept);
		super.put("deptList",deptList);
		return "toupdate";
	}
	
	@Action(value="roleAction_update")
	public String update(){
		System.out.println("update d="+d);
		Role d1=ds.get(d.getId());
		d1.setName(d.getName());
		d1.setRemark(d.getRemark());
		ds.saveOrUpdate(d1);
		return "action_list";
	}
	@Action(value="roleAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
		ds.delete(a.split(","));
//		for(String ss:a.split(",")){
//			ds.dg_delete(ss);
//		}
		return "action_list";
	}
	@Action(value="roleAction_tomodule",results=@Result(name="tomodule",
			location="/WEB-INF/pages/sysadmin/role/jRoleModule.jsp"))
	public String tomodule() throws SysException{	
		try {
			d=ds.get(d.getId());
			super.push(d);
		} catch (Exception e) {
			throw new SysException("请先勾选一个");
		}
//		Set<String> roleModuleStr =new HashSet();
//		for(Module m1:d.getModules()){
//			roleModuleStr.add(m1.getId());
//		}
//		super.put("roleModuleStr",roleModuleStr);
//		System.out.println(" tomodule roleModuleStr="+roleModuleStr);
//		List<Module> moduleList = ms.find(null);
////		System.out.println("moduleList="+moduleList);
//		super.put("moduleList",moduleList);
		return "tomodule";
	}
	
	@Action(value="roleAction_module")
	public String module(){
		moduleIds=moduleIds.replaceAll("\\s+","");
		System.out.println("module moduleIds="+moduleIds);
		Role d1=ds.get(d.getId());
		d1.getModules().clear();
		for(String ri:moduleIds.split(",")){
			if(ri.length()>0){
				d1.getModules().add(ms.get(ri));
			}
		}
		ds.saveOrUpdate(d1);
		Jedis j = pool.getResource();
		j.del("modules_"+d.getId());
		return "action_list";
	}
	
	//相应role里模块勾选页面的ajax请求
	@Action(value="roleAction_genzTreeNodes")
	public String tomodule1() throws IOException{	
		d=ds.get(d.getId());
		
		
		//生成json方法1：拼接方法：
//		StringBuilder sb=new StringBuilder();
//		sb.append("[");
//		for(Module m:md){
//			sb.append("{\"id\":\"").append(m.getId()).append("\",");
//			sb.append("\"pId\":\"").append(m.getParentId()).append("\",");
//			if(dd.contains(m)){
//				sb.append("\"checked\":\"true\",");
//			}
//			sb.append("\"name\":\"").append(m.getName()).append("\"},");	
//		}
//		sb.replace(sb.length()-1, sb.length(),"");
//		sb.append("]");
		
//		Jedis j = pool.getResource();
		ValueOperations j = r.opsForValue();
		System.out.println("j="+j);
		String sb=(String) j.get("modules_"+d.getId());
		if (sb==null) {
			System.out.println("come from oracle");
			Set<Module> dd = d.getModules();
			List<Module> md = ms.find(specModule);
			//生成json方法2：json转换：
			List a = new ArrayList();
			Gson g = new Gson();
			for (Module m : md) {
				Map map = new HashMap();
				map.put("id", m.getId());
				map.put("pId", m.getParentId());
				map.put("name", m.getName());
				if (dd.contains(m)) {
					map.put("checked", "true");
				}
				a.add(map);
			}
			sb = g.toJson(a);
			j.set("modules_"+d.getId(),sb);
		}
		System.out.println("sb json="+sb);
		HttpServletResponse rep = ServletActionContext.getResponse();
		rep.setCharacterEncoding("UTF-8");
		rep.getWriter().write(sb);
	return null;
	}

}
