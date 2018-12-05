package cn.itcast.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.itcast.springmvc.pojo.Items;

public class ItemsController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		模拟数据
		List<Items> list = new ArrayList<Items>();
		
		for (int i = 0; i < 10; i++) {
			Items items = new Items();
			items.setId(i);
			items.setCreatetime(new Date());
			items.setName("小米手机"+i);
			items.setDetail("国产");
			items.setPrice((float) (1000*i));
			list.add(items);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", list); //相当于把数据放到了request域中
		
//		逻辑视图：就是jsp的路径
		modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
		
		return modelAndView;
	}
	

}
