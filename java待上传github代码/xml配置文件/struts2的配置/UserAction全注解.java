package cn.me.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.me.domain.User;
import cn.me.service.UserService;

@Controller
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace(value = "/a/*")

//相当于配置global-results
@Results({@Result(name="success",location="/success.jsp")})
public class UserAction extends ActionSupport implements ModelDriven<User> {
	@Autowired
	private UserService us;
	private User u = new User();

	//可以访问的连接：/a/c1/x,/a/dd/y
	//不可以访问的连接：/a/a/c1/x
	//return s1则访问/1.jsp，return s2则访问/success.jsp
	@Actions({ @Action(value = "x", results = { @Result(name = "s1", location = "/1.jsp") }),
			@Action(value = "y", results = { @Result(name = "s1", location = "/1.jsp") }) })
	public String t() {
		System.out.println(us);
		Integer c = u.getCode();
		System.out.println(c + "\t" + u);
		if (c > 0) {
			return "s1";
		}
		return "s2";
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return u;
	}
}

