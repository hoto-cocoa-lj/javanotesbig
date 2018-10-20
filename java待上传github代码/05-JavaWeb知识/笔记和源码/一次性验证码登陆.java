package com.heima.cookie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

//没有做优化
//通过r.getSession()的attri从验证码生成页面获取验证码code（因为不在同一个request里，
//只能用session或者context），读取成功后清空该attri，防止用户后退后还能用原来的验证码登陆。
//通过request的attr从登陆页面获取用户输入的验证码，

public class Deletecookie extends HttpServlet {

	public void doGet(HttpServletRequest r, HttpServletResponse s) {

	}

	public void doPost(HttpServletRequest r, HttpServletResponse s) throws IOException, ServletException {
		s.setHeader("content-type", "text/html;charset=utf-8");
		String code = (String) r.getSession().getAttribute("code");
		System.out.println();
		r.getSession().removeAttribute("code");
		String c = r.getParameter("code").trim().toUpperCase();
		String z = "";
		System.out.println("code=" + code + "\tc=" + c + "\t" + (c.equalsIgnoreCase(code)));
		if (c.length() == 0) {
			z = "请输入验证码";
			r.setAttribute("z", z);
			r.getRequestDispatcher("/indexb.jsp").forward(r, s);
		} else if (c.equalsIgnoreCase(code)) {
			try {
				z = getr(r);
				s.getWriter().print(z);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			z = "验证码错误";
			r.setAttribute("z", z);
			r.getRequestDispatcher("/indexb.jsp").forward(r, s);
		}

	}

	private String getr(HttpServletRequest r) throws SQLException {
		String u = r.getParameter("u");
		String p = r.getParameter("p");
		String z = "";
		Object[] x = new Dao().select(u, p);
		if (x.length == 0) {
			z = "账号or密码错误";
		} else {
			z = "欢迎回来" + Arrays.toString(x);
		}
		return z;
	}

}
