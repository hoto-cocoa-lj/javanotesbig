package cn.itcast.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.springmvc.pojo.Items;

@Controller
public class ItemsController2{

	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		模拟数据
		List<Items> list = new ArrayList<Items>();
		
		for (int i = 0; i < 10; i++) {
			Items items = new Items();
			items.setId(i);
			items.setCreatetime(new Date());
			items.setName("华为手机"+i);
			items.setDetail("也是国产");
			items.setPrice((float) (1000*i));
			list.add(items);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", list); //相当于把数据放到了request域中
		
//		逻辑视图：就是jsp的路径
		modelAndView.setViewName("itemList");
		
		return modelAndView;
	}
	
	@RequestMapping("/list2")
	public ModelAndView list2(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		模拟数据
		List<Items> list = new ArrayList<Items>();
		
		for (int i = 0; i < 10; i++) {
			Items items = new Items();
			items.setId(i);
			items.setCreatetime(new Date());
			items.setName("苹果手机"+i);
			items.setDetail("一般般");
			items.setPrice((float) (1000*i));
			list.add(items);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", list); //相当于把数据放到了request域中
		
//		逻辑视图：就是jsp的路径
		modelAndView.setViewName("itemList");
		
		return modelAndView;
	}
	

}
