package cn.me.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.itcast.utils.SysConstant;
import cn.me.domain.User;

/**
 * @Description: 登录和退出类
 * @Author: 传智播客 java学院 传智.宋江
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月31日
 * 
 *              继承BaseAction的作用 1.可以与struts2的API解藕合 2.还可以在BaseAction中提供公有的通用方法
 */
@Namespace("/")
@Results({ @Result(name = "login", location = "/WEB-INF/pages/sysadmin/login/login.jsp"),
		@Result(name = "success", location = "/WEB-INF/pages/home/fmain.jsp"),
		@Result(name = "logout", location = "/index.jsp") })
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;


	@Action("loginAction_login")
	public String login() throws Exception {
		if (username == null) {
			return "login";
		}
		Subject subject = SecurityUtils.getSubject();

		// MyPasswordMatcher就是用了下面这个md5hash来加密的，如果在这里就转换密码再传过去，
		// 就可以不要MyPasswordMatcher了。不在这里设置的原因是不想暴露加密方法
		// password = new Md5Hash(password, username, 2).toString();

		// 把账号密码封装进token，之后调用authRealm的方法来验证
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		try {
			// 验证失败会报错
			subject.login(token);
			User user = (User) subject.getPrincipal();
			getSession().put(SysConstant.CURRENT_USER_INFO, user);
			return SUCCESS;
		} catch (Exception e) {
			put("errorInfo", "账号密码有误");
			return "login";
		}
	}

	// 退出
	@Action("loginAction_logout")
	public String logout() {
		session.remove(SysConstant.CURRENT_USER_INFO); // 删除session
		Subject subject = SecurityUtils.getSubject();
		subject.logout(); // 登出
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
