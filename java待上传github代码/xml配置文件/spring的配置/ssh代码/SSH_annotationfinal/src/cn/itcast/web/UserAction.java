package cn.itcast.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;

@Controller("userAction")
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
public class UserAction extends ActionSupport implements ModelDriven<User>
{
	
	@Autowired
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Action(value="user_save",results={@Result(name="success",location="/success.jsp")})
	public String save()
	{
		userService.save(user);
		return SUCCESS;
	}

	
	
	
	// 封装页面的数据
	private User user =new User();
	@Override
	public User getModel() {
		
		return user;
	}
}
