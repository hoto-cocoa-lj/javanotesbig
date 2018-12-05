package cn.itcast.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object method,
			Exception exception) {
		
//		跳转到错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", exception.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
