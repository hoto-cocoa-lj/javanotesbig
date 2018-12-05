package cn.itcast.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.pojo.QueryVo;
import cn.itcast.ssm.pojo.QueryVo2;
import cn.itcast.ssm.service.ItemService;


@Controller
@RequestMapping("/item") 
public class ItemsController{
	@Autowired
	private ItemService itemService; 
	
	@RequestMapping(value={"/list","/cao"})
	public ModelAndView showList(QueryVo queryVo,int[] ids){
		System.out.println("showList queryVo="+queryVo);
		List<Items> list1 = itemService.findAll(queryVo);
		ModelAndView m = new ModelAndView();
		m.addObject("itemList",list1);
		m.setViewName("itemList");
		return m;
	}
	
	@RequestMapping("/list2")
	public ModelAndView showList2(QueryVo queryVo,int[] ids,HttpServletRequest r){
		r.getSession().setAttribute("user","sb");
		System.out.println(1/0);
		System.out.println("showList queryVo="+queryVo);
		List<Items> list1 = itemService.findAll(queryVo);
		
		ModelAndView m = new ModelAndView();
		m.addObject("itemList",list1);
		m.setViewName("itemList2");
		return m;
	}

	@RequestMapping("/list3")
	public ModelAndView showList3(QueryVo queryVo,ArrayList<Items> itemList){ 
		List<Items> list = itemService.findAll(queryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", list);
		modelAndView.setViewName("itemList3");
		return modelAndView;
	}
	
	@RequestMapping("/itemEdit/{thisid}")
	public String editItemRestful(@PathVariable("thisid") int iid,Model model) throws Exception{
		System.out.println(iid);
		Items i = itemService.findByID(iid);
		if(i==null){
			throw new Exception("没有找该到id");
		}
		model.addAttribute("item", i);
		return "editItem";
	}
	
	@RequestMapping("/itemEdit")
	public String editItem(@RequestParam(value="id") int iid,Model model) throws Exception{
		System.out.println(iid);
		Items i = itemService.findByID(iid);
		if(i==null){
			throw new Exception("没有找该到id");
		}
		model.addAttribute("item", i);
		return "editItem";
	}
	
	@RequestMapping("/updateitem")
	public String updateitem(Items items,MultipartFile pictureFile) throws IllegalStateException, IOException{
		System.out.println(items);
		String ss=pictureFile.getOriginalFilename();
		int lastIndexOf = ss.lastIndexOf(".");
		String s1=UUID.randomUUID().toString();
		ss=s1+ss.substring(lastIndexOf);
		System.out.println(lastIndexOf+"\t"+ss);
		items.setPic(ss);
		pictureFile.transferTo(new File("d:/up/"+ss));
		itemService.update(items);
		return "redirect:list.action";		
	}
//	@RequestMapping("/updateitem")
//	public String updateitem(Items items) throws IllegalStateException, IOException{
//		System.out.println(items);
//		return "redirect:list.action";		
//	}
	
	@RequestMapping("/updateitemajax")
	@ResponseBody
	public Map updateitemajax(Items items){
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println(items);
		HashMap m = new HashMap();
		try{
/*			String ss=pictureFile.getOriginalFilename();
			int lastIndexOf = ss.lastIndexOf(".");
			String s1=UUID.randomUUID().toString();
			ss="d:/up/"+s1+ss.substring(lastIndexOf);
			System.out.println(lastIndexOf+"\t"+ss);
			items.setPic(ss);
			pictureFile.transferTo(new File(ss));*/
			itemService.update(items);
			m.put("success",true);
		}catch (Exception e) {
			System.out.println(e);
		}
		return m;
	}
	
}
