package cn.itcast.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyIterceptor1 implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
			Object paramObject) throws Exception {
		// TODO Auto-generated method stub
		Object u = paramHttpServletRequest.getSession().getAttribute("user");
		HandlerMethod h=(HandlerMethod) paramObject;
		String name = h.getMethod().getName();
		System.out.println("name="+name);
		if("showList2".equals(name)){
			return true;
		}
		if(u!=null){
			System.out.println(this.getClass().getName()+"前置方法");
			return true;
		}
		String servletPath = paramHttpServletRequest.getContextPath();
		System.out.println("servletPath="+servletPath);
		paramHttpServletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(paramHttpServletRequest, paramHttpServletResponse);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
			Object paramObject, ModelAndView paramModelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName()+"中间方法");
	}

	@Override
	public void afterCompletion(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName()+"后面方法");
	}

}
