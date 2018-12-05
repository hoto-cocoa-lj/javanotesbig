package cn.itcast.ssm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.common.utils.PageBean;
import cn.itcast.ssm.pojo.BaseDict;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.QueryVo;
import cn.itcast.ssm.service.BaseDictService;
import cn.itcast.ssm.service.CustomerService;

@Controller
public class CustomerController {
	
	@Value("${base_dict.source}")
	private String baseDictSource;
	@Value("${base_dict.industry}")
	private String baseDictIndustry;
	@Value("${base_dict.level}")
	private String baseDictLevel;
	
	@Autowired
	private BaseDictService baseDictService;
	
	@Autowired
	private CustomerService  customerService;
	
	@RequestMapping("/list")
	public String list(QueryVo queryVo,Model model) throws Exception{
//		查询客户来源、客户行业、客户的等级
		List<BaseDict> sourceList = baseDictService.findByTypecode(baseDictSource );
//		客户行业
		List<BaseDict> industryList = baseDictService.findByTypecode(baseDictIndustry);
//		客户的等级
		List<BaseDict> levelList =baseDictService.findByTypecode(baseDictLevel);
		
//		初始化查询数据
		model.addAttribute("sourceList", sourceList);
		model.addAttribute("industryList", industryList);
		model.addAttribute("levelList", levelList);
		
		if(queryVo.getCurrentPage()==0){
			queryVo.setCurrentPage(1);
		}
		if(queryVo.getPageSize()==0){
			queryVo.setPageSize(5);
		}
		queryVo.setStart((queryVo.getCurrentPage()-1)*queryVo.getPageSize());
		
		
//		回显数据
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		
		if(queryVo.getCustName()!=null && !queryVo.getCustName().equals("")){
			String custName = new String(queryVo.getCustName().getBytes("iso-8859-1"),"utf-8");
			model.addAttribute("custName", custName);
			queryVo.setCustName(custName);
		}
		
//		获取分页数据
		PageBean pageBean = customerService.findByPage(queryVo);
		model.addAttribute("page", pageBean);
		
		
		
		return "customer";
	}
	
	@RequestMapping("/edit")
	@ResponseBody //作用 把对象转成json字符串，并且回写浏览器
	public Customer edit(long id){
		Customer customer = customerService.findById(id);
		return customer;
	}
	@RequestMapping("/update")
	@ResponseBody //作用 把对象转成json字符串，并且回写浏览器
	public boolean update(Customer customer){
		try {
			customerService.update(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@RequestMapping("/delete")
	@ResponseBody //作用 把对象转成json字符串，并且回写浏览器
	public boolean delete(long id){
		try {
			customerService.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
