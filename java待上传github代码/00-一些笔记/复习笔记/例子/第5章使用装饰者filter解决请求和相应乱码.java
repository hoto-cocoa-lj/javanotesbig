
package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class S6 implements Filter {
	private FilterConfig c;

	public void init(FilterConfig paramFilterConfig) throws ServletException {
		System.out.println(" filter init");
		this.c = paramFilterConfig;
	}

	public void doFilter(ServletRequest r, ServletResponse s, FilterChain c) throws IOException, ServletException {
		System.out.println("filter=" + r.getClass());
		HttpServletRequest r1 = (HttpServletRequest) r;
		R r2 = new R(r1);

		HttpServletResponse s2 = (HttpServletResponse) s;
		s2.setContentType("text/html;charset=utf-8");
		c.doFilter(r2, s2);

	}

	public void destroy() {
		System.out.println(" filter destroy ");

	}

}

class R extends HttpServletRequestWrapper {

	public R(HttpServletRequest request) {
		super(request);
	}

	
	/*注释掉的是我自己写的方法，有问题。如果是普通的表单请求，我的方法可以处理
	request的post的中文乱码，但是ajax的post请求，无论中文在不在对象里，
	都会显示乱码。而且不做任何处理的话反而能正常显示。
	使用没注释的代码不会有这个问题。
	
	@Override
	public String getParameter(String name) {
		String s = super.getParameter(name);
		try {
			s = new String(s.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}*/
	
	@Override
	public String getParameter(String name) {
		String method = super.getMethod();
		if("get".equalsIgnoreCase(method)){
			String value = super.getParameter("username");
			try {
				value = new String(value.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return value;
		}else if("post".equalsIgnoreCase(method)){
			try {
				super.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return super.getParameter(name);
		}
		return super.getParameter(name);
	}

}
