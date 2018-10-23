package cn.itcast.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>
{
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
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
