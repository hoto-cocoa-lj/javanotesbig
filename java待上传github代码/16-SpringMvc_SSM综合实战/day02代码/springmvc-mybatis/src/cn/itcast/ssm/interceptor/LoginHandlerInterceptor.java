package cn.itcast.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object method) throws Exception {
//		访问 list.action时判断是否有当前登录人，如果有执行访问，如果没有跳转到登录页面
		
		Object attribute = request.getSession().getAttribute("user");
		if(attribute==null){ //代表没有当前登录人
//			判断方法是否为login方法，如果是直接放行，如果不是则拦截
			HandlerMethod handlerMethod = (HandlerMethod) method;
			if(handlerMethod.getMethod().getName().equals("login")){
				return true;
			}
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return false;  //拦截
		}else{
			return true;  //放行
		}
		
//		return false;  //拦截

	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("执行了MyHandlerInterceptor2的执行方法......");

	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("执行了MyHandlerInterceptor2的后置方法......");

	}

	

	

}
