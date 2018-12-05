package cn.itcast.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.pojo.QueryVo;
import cn.itcast.ssm.service.ItemService;

@Controller
@RequestMapping("/items/")
public class ItemsController {

	@Autowired
	private ItemService itemService ;
	
	@RequestMapping(value={"/list","/list2","/itemList"},method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView showList(QueryVo queryVo,int[] ids){
		
//		int i = 10/0;
		List<Items> list = itemService.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", list);
		modelAndView.setViewName("itemList");
		return modelAndView;
	}
//	itemEdit.action?id=1
//	展示修改页面
	@RequestMapping("/itemEdit/{id}")  ////	itemEdit/1
//	public ModelAndView itemEdit(int id,HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
//    public String itemEdit(@RequestParam(value="id",required=false,defaultValue="1")int itemId,HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
	public void itemEdit(@PathVariable("id")int itemId,HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model){
		Items items = itemService.findById(itemId);
//		model.addAttribute("item", items);
//		逻辑视图：jsp的路径
		
//		此方式不通过springmvc容器，不适用视图解析器
		try {
			request.setAttribute("item", items);
			request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp").forward(request, response);
			
//			response.sendRedirect("/WEB-INF/jsp/editItem.jsp");
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		return "editItem";
	}
	
//	商品修改
	@RequestMapping("/updateitem")
	public String updateitem(Items item, MultipartFile pictureFile,  Model model) throws Exception, IOException{
//		存放到d:\\uploadFile,并且文件改名
//		pictureFile.getOriginalFilename() “hsdkj.aef.台式机.jpg”
		
		String originalFilename = pictureFile.getOriginalFilename();//获取上传文件的完整名称（带扩展名）
		String fileName = UUID.randomUUID().toString(); //创建一个随机数，作为即将保存图片的名字
		String ext = originalFilename.substring(originalFilename.lastIndexOf(".")); //获取文件的扩展名
		pictureFile.transferTo(new File("d:\\uploadFile\\"+fileName+ext));  //文件的保存
		item.setPic(fileName+ext);  //文件名称保持到表中
		itemService.update(item);
//		逻辑视图：jsp的路径
//		重定向
		return "redirect:list.action";  //通知浏览器，让浏览器重新发起请求，使用的两个不同的request域
//		请求转发
//		return "forward:list.action";  //浏览器保留原有的请求地址，使用的是同一个request域
	}
	
	@RequestMapping("/updateitemAjax")
	@ResponseBody  //作用 把即将返回的对象转成json字符串并且回写到浏览器
	public Map updateitemAjax(@RequestBody Items item){  //@RequestBody 强制要求传入的参数类型是json
//		data :{"success":true|false,"message":"操作成功"|“操作失败”}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			itemService.update(item);
			map.put("success", true);
			map.put("message", "操作成功");
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", "操作失败");
//			e.printStackTrace();
		}
		return map;
		
	}
	
}
