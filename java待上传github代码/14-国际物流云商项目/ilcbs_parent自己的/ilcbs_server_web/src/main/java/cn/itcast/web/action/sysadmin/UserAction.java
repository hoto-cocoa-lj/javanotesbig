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
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.service.DeptService;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import cn.itcast.utils.Page;
import cn.itcast.web.action.BaseAction;

@Namespace("/sysadmin")
@Result(type="redirectAction",name ="action_list",location="userAction_list")
public class UserAction extends BaseAction<User> implements ModelDriven<User>{
	@Autowired
	private UserService ds;
	@Autowired
	private DeptService dsDept;
	@Autowired
	private RoleService rs;
	private User d=new User();
	private String  roleIds;
	
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	private 		// userList,0停用的就不要了
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


	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return d;
	}
	
	@Action(value="userAction_list",results={@Result(name="list",location=
			"/WEB-INF/pages/sysadmin/user/jUserList.jsp")})
	public String list() {
		org.springframework.data.domain.Page<User> page2 = ds.
				findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("userAction_list");
		super.push(page);
		return "list";
	}
	
	@Action(value="userAction_toview",results={@Result(name="toview",location=
			"/WEB-INF/pages/sysadmin/user/jUserView.jsp")})	
	public String toview(){
		d=ds.get(d.getId());
		super.push(d);
		return "toview";
	}
		
	@Action(value="userAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/sysadmin/user/jUserCreate.jsp")})	
	public String tocreate(){
		List<Dept> deptList=dsDept.find(specDept);
//		System.out.println("tocreate deptList="+deptList);
		super.put("deptList",deptList);
		List<User> userList=ds.find(null);
		super.put("userList",userList);	
		return "tocreate";
	}
	
	@Action(value="userAction_insert")
	public String insert(){
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="userAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/sysadmin/user/jUserUpdate.jsp")})	
	public String toupdate(){
		d=ds.get(d.getId());
		super.push(d);		
		List<Dept> deptList=dsDept.find(specDept);
		super.put("deptList",deptList);
		return "toupdate";
	}
	
	@Action(value="userAction_update")
	public String update(){
		System.out.println("update d="+d);
		User d1=ds.get(d.getId());
		d1.setState(d.getState());
		d1.getUserinfo().setName(d.getUserinfo().getName());
		d1.setDept(dsDept.get(d.getDept().getId()));

		ds.saveOrUpdate(d1);
		return "action_list";
	}
	@Action(value="userAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
		ds.delete(a.split(","));
//		for(String ss:a.split(",")){
//			ds.dg_delete(ss);
//		}
		return "action_list";
	}
	@Action(value="userAction_torole",results=@Result(name="torole",
			location="/WEB-INF/pages/sysadmin/user/jUserRole.jsp"))
	public String torole(){	
		System.out.println("torole id="+d.getId());
		d=ds.get(d.getId());
		super.push(d);
		
		StringBuilder roleStr=new StringBuilder();
		for(Role r:d.getRoles()){
			roleStr.append(r.getName()).append(",");
		}
		super.put("roleStr",roleStr);
		List<Role> roleList=rs.find(null);
		super.put("roleList",roleList);
		return "torole";
	}
	
	@Action(value="userAction_role")
	public String role(){
		roleIds=roleIds.replaceAll("\\s+","");
		User d1=ds.get(d.getId());
		d1.getRoles().clear();
		for(String ri:roleIds.split(",")){
			d1.getRoles().add(rs.get(ri));
		}
		ds.saveOrUpdate(d1);
		return "action_list";
	}
}
