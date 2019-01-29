package cn.me.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import cn.me.domain.User;
import cn.me.service.UserService;


//servlet不受spring管理，所以要手动注入，使用方法是重写init方法
public class S1 extends HttpServlet {
	private UserService us;

	@Override
	public void init() throws ServletException {
		super.init();
		String xml = "applicationContext.xml";
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(xml);
		us = c.getBean(UserService.class);
	}

	public void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		User u = new User();
		String parameter = r.getParameter("a");
		int i = Integer.parseInt(parameter);
		u.setUser_code(i);
		us.save(u);
	}

	public void doPost(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		doGet(r, s);
	}

}
