package cn.itcast.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// this : 谁来调用代表谁 userServlet categoryServlet productServlet
			Class clazz = this.getClass();
			// 获取方法  传什么 获取什么
			// 通过字节码对象获取到类指定的方法
				// 参数1: 方法的名字
				// 参数2: 方法的参数类型
			Method method = clazz.getMethod(request.getParameter("method"),HttpServletRequest.class,HttpServletResponse.class);
			// 获取到让方法执行
			String value =(String)method.invoke(clazz.newInstance(), request,response);
			// 做统一的请求转发
			if(value!=null)
			{
				request.getRequestDispatcher(value).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
