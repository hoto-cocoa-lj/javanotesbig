package cn.itcast.ssm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	
	@Value("${base_dict.source}")
	private String  baseDictSource;
	@Value("${base_dict.industry}")
	private String  baseDictIndustry;
	@Value("${base_dict.level}")
	private String  baseDictLevel;
	
	@RequestMapping(value={"/list"})
	public String list(Model model,QueryVo queryVo){
		List<BaseDict> a2 = baseDictService.findByTypeCode(baseDictSource);
		List<BaseDict> a1 = baseDictService.findByTypeCode(baseDictIndustry);
		List<BaseDict> a6 = baseDictService.findByTypeCode(baseDictLevel);
		model.addAttribute("sourceList",a2);
		model.addAttribute("levelList",a6);
		model.addAttribute("industryList",a1);	
		
		int pageSize=5;
		queryVo.setPageSize(pageSize);
		System.out.println("queryVo="+queryVo);
		int currentPage=queryVo.getCurrentPage();

		System.out.println(currentPage+"\t"+pageSize);
		PageBean pagebean = new PageBean();
		
		int total = customerService.findTotal(queryVo);
		Double p= total*1.0/pageSize;
		int totalCount=(int) Math.ceil(p);
		System.out.println("totalCount="+totalCount+"\tp="+p);
		
		List<Customer> list = customerService.findPage(queryVo);
		System.out.println("list.size="+list.size());
		pagebean.setCurrentPage(currentPage);
		pagebean.setList(list);
		pagebean.setPageSize(pageSize);
		pagebean.setTotalCount(totalCount);
				
		
		model.addAttribute("page",pagebean);
		model.addAttribute("queryVo",queryVo);
		System.out.println("pagebean="+pagebean+"\tqueryVo="+queryVo);
		return "customer";
	}
	
	@RequestMapping(value={"/edit"})
	@ResponseBody
	public Customer edit(Model model,long id){
		Customer s = customerService.getById(id);
		System.out.println(id+"\tcustomerService.getById(id)="+s);
		return s;
	}
	
	@RequestMapping(value={"/update"})
	@ResponseBody
	public boolean update(Customer c){		
		try {
			customerService.save(c);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(value={"/delete"})
	@ResponseBody
	public boolean delete(long id){		
		try {
			customerService.delete(id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
