package cn.itcast.web.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.dao.ExportProductDao;
import cn.itcast.dao.PackingListDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.Export;
import cn.itcast.domain.ExportProduct;
import cn.itcast.domain.ExportProductResult;
import cn.itcast.domain.ExportProductVo;
import cn.itcast.domain.ExportResult;
import cn.itcast.domain.ExportVo;
import cn.itcast.domain.Factory;
import cn.itcast.domain.MREP;
import cn.itcast.domain.PackingList;
import cn.itcast.export.webservice.Exception_Exception;
import cn.itcast.export.webservice.IEpService;
import cn.itcast.service.ContractService;
import cn.itcast.service.ExportProductService;
import cn.itcast.service.ExportService;
import cn.itcast.service.FactoryService;
import cn.itcast.service.PackingListService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.HttpClientUtil;
import cn.itcast.utils.Page;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Result(type="redirectAction",name="action_list",location="packingListAction_list")
public class PackingListAction extends BaseAction<PackingList> implements ModelDriven<PackingList>{
	@Autowired
	private ExportService exportService;
	@Autowired
	private PackingListService pls;
	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	@Autowired
	private PackingListService packingListService;
	@Autowired
	private PackingListDao packingListDao;

	private PackingList model=new  PackingList();
	@Override
	public PackingList getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	@Action(value="packingListAction_list",results={@Result(name="list",
			location="/WEB-INF/pages/cargo/packinglist/jPackingListCreate.jsp")})
	public String list(){
		Specification<Export> spec = new Specification<Export>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<Export> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
//				return null;
				return cb.notEqual(root.get("state").as(Integer.class),0);
			}
		};
		List<Export> exportList = exportService.find(spec);
		super.put("exportList",exportList);	
		return "list";
	}
	@Action(value="packingListAction_insert")
	public String insert(){
		packingListService.save(model);
		return "action_list";
	}
	
	@Action(value="shippingOrderAction_list",results={@Result(name="list",
			location="/WEB-INF/pages/cargo/packinglist/weituodan.jsp")})
	public String list1(){
		org.springframework.data.domain.Page<PackingList> page2 = pls.
				findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("shippingOrderAction_list");
		super.push(page);	
		return "list";
	}
	
}
