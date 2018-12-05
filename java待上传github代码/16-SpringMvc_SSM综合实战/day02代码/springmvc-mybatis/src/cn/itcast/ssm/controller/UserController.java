package cn.itcast.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.pojo.QueryVo;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@RequestMapping("/toLogin")
	public String  showList(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String  login(String username,String password,HttpSession session){
		session.setAttribute("user", username);
		return "success";
	}
	
}
