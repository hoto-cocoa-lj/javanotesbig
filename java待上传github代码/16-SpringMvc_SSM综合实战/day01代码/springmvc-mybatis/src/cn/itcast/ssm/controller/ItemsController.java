package cn.itcast.ssm.controller;

import java.util.List;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.pojo.QueryVo;
import cn.itcast.ssm.service.ItemService;

@Controller
public class ItemsController {

	@Autowired
	private ItemService itemService ;
	
	@RequestMapping("/list")
	public ModelAndView showList(QueryVo queryVo){
		List<Items> list = itemService.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", list);
		modelAndView.setViewName("itemList");
		return modelAndView;
	}
//	itemEdit.action?id=1
//	展示修改页面
	@RequestMapping("/itemEdit")
//	public ModelAndView itemEdit(int id,HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
    public String itemEdit(@RequestParam(value="id",required=false,defaultValue="1")int itemId,HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
		Items items = itemService.findById(itemId);
		model.addAttribute("item", items);
//		逻辑视图：jsp的路径
		return "editItem";
	}
	
//	商品修改
	@RequestMapping("/updateitem")
	public String updateitem(Items item,Model model){
		itemService.update(item);
		
//		逻辑视图：jsp的路径
		return "success";
	}
	
}
