package cn.itcast.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.serviceimpl.UserServiceImpl;

//注意如果想用全局拦截器拦截本类，这里要配置@ParentPackage("test1")
@ParentPackage("struts-default")
@Namespace("/")
public class UserAction extends ActionSupport implements ModelDriven<User>
{
	private  User user=new User();
	
	/*private String message;
	public String getMessage() {
		return message;
	}*/

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	@Action(value="user_login",results={@Result(name="login",location="/login.jsp"),@Result(name="index",location="/index.htm")})
	public String login()
	{
		// 拿着用户名和密码去匹配
		UserService userService=new UserServiceImpl();
		User userFind=userService.login(user);
		//判断
		if(userFind==null)
		{
			// 到登录页面展示  用户名或密码错误
			// ActionSupport的方法  会自动在成员位置上创建一个属性ActionMessage 并赋值
			addActionMessage("用户名或密码错误"); //企业常用的
			
			return "login";
			
		}
		// 放在session中 带到首页展示
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", userFind);
		
		return "index";
	}

	
}
