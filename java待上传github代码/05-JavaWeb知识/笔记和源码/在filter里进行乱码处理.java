package com.itheima.servlet;

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

/*
装饰者模式：

思路是用filter对所有访问进行乱码处理，即在filter里重写request的getparameter方法，
获取表格的参数乱码处理后再返回。重写这个方法需要完成两点，
第一：必须返回一个request，所以不能用接口实现，常规方法是定义一个类，
类的构造函数能返回原来的request；
第二：必须在自定义的getparameter方法里调用现成的getparameter，所以不能用接口实现；

所以使用了HttpServletRequestWrapper，这个方法部分源码如下：
	HttpServletRequestWrapper extends ServletRequestWrapper implements HttpServletRequest，
    public HttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

如何使用装饰者模式：
1：装饰者和被装饰者继承同一个类或者实现同一个接口
	（能调用现成的getparameter，能返回一个request，还能使用原来的request的所有属性和方法）
2：装饰者中需要用被装饰者的引用（指chain.doFilter(new myCode(request), res)这句代码）
3：对需要增强的方法进行增强
4：对不需要增强的方法直接使用父类的方法

*/

public class CodeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		chain.doFilter(new myCode(request), res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

class myCode extends HttpServletRequestWrapper{

	public myCode(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
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
