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
import cn.itcast.export.webservice.Exception_Exception;
import cn.itcast.export.webservice.IEpService;
import cn.itcast.service.ContractService;
import cn.itcast.service.ExportProductService;
import cn.itcast.service.ExportService;
import cn.itcast.service.FactoryService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.HttpClientUtil;
import cn.itcast.utils.Page;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Result(type="redirectAction",name="action_list",location="exportAction_list")
public class ExportAction extends BaseAction<Export> implements ModelDriven<Export>{
	@Autowired
	private ExportService ds;
	@Autowired
	private ExportProductDao epd;
	@Autowired
	private IEpService ies;
	@Autowired
	private ExportProductService eps;
	@Autowired
	private ContractService cs;
	@Autowired
	private FactoryService fs;
	
	private MREP mr;
	
	public MREP getMr() {
		return mr;
	}

	public void setMr(MREP mr) {
		this.mr = mr;
	}

	private Export d=new Export();

	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public Export getModel() {
		// TODO Auto-generated method stub
		return d;
	}

	@Action(value="exportAction_contractList",results={@Result(name="contractList",
			location="/WEB-INF/pages/cargo/export/jContractList.jsp")})	
	public String contractList(){
		Specification<Contract> spec = new Specification<Contract>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
//				return null;
				return cb.notEqual(root.get("state").as(Integer.class),0);

			}
		};
		org.springframework.data.domain.Page<Contract> page2 = cs.
				findPage(spec, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("exportAction_contractList");
		super.push(page);		
		return "contractList";
	}
	
	@Action(value="exportAction_list",results={@Result(name="list",
			location="/WEB-INF/pages/cargo/export/jExportList.jsp")})	
	public String list(){
		Specification<Export> spec = new Specification<Export>() {

			// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
			// ContractProduct where state = 1
			@Override
			public Predicate toPredicate(Root<Export> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return null;
//				return cb.notEqual(root.get("state").as(Integer.class),0);

			}
		};
		org.springframework.data.domain.Page<Export> page2 = ds.
				findPage(spec, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("exportAction_list");
		super.push(page);		
		return "list";
	}
	
	
	@Action(value="exportAction_insert")
	public String insert(){
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="exportAction_tocreate",results={@Result(name="tocreate"
			,location="/WEB-INF/pages/cargo/export/jExportCreate.jsp")})
	public String tocreate(){
//		d.setContractIds(d.getId());
		return "tocreate";
	}

	@Action(value="exportAction_toview",results={@Result(name="toview"
			,location="/WEB-INF/pages/cargo/export/jExportView.jsp")})
	public String toview(){
		super.push(ds.get(d.getId()));
		return "toview";
	}
	
	@Action(value="exportAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/cargo/export/jExportUpdate.jsp")})	
	public String toupdate(){
		super.push(ds.get(d.getId()));
		return "toupdate";
	}
	
	@Action(value="exportAction_update")	
	public String update(){
		System.out.println("mr="+mr);
		Export e = ds.get(d.getId());
		e.setInputDate(d.getInputDate());
		e.setLcno(d.getLcno());
		e.setConsignee(d.getConsignee());
		e.setShipmentPort(d.getShipmentPort());
		e.setPriceCondition(d.getPriceCondition());
		e.setMarks(d.getMarks());
		e.setRemark(d.getRemark());
		
		for(int i=0;i<mr.getMr_id().length;i++){
			String z=mr.getMr_changed()[i].trim();
			System.out.println("???????????????????z="+z+"\t"+("1".equals(z)));
			if("1".equals(z)){
				System.out.println("!!!!!!!!!");
				ExportProduct e1 = eps.get(mr.getMr_id()[i]);
				System.out.println("i="+i+"\te1="+e1);
				e1.setCnumber(mr.getMr_cnumber()[i]);
				e1.setGrossWeight(mr.getMr_grossWeight()[i]);
				e1.setNetWeight(mr.getMr_netWeight()[i]);
				e1.setSizeLength(mr.getMr_sizeLength()[i]);
				e1.setSizeWidth(mr.getMr_sizeWidth()[i]);
				e1.setSizeHeight(mr.getMr_sizeHeight()[i]);
				e1.setExPrice(mr.getMr_exPrice()[i]);
				e1.setTax(mr.getMr_tax()[i]);
				System.out.println("e1="+e1);
				eps.saveOrUpdate(e1);
			}
		}
		ds.saveOrUpdate(e);
		return "action_list";
	}

	@Action(value="exportAction_delete")
	public String delete(){	
		String[] ss = d.getId().split(", ");		
		for(String s:ss){
			System.out.println("将id是s的export的订单设为状态0,s="+s);
			String ss1 = ds.get(s).getContractIds();
			String[] ss2 = ss1.split(", ");
			for(String x:ss2){
				System.out.println("将id是x的contract的订单设为状态0,x="+x);
				cs.get(x).setState(0);
			}
		}
		ds.delete(ss);
		return "action_list";
	}
	
	@Action(value="exportAction_submit")
	public String submit(){	
		String a=d.getId();
		for(String b:a.split(", ")){
			Export c=ds.get(b);
			c.setState(1);
			ds.saveOrUpdate(c);
		}
		return "action_list";
	}
	@Action(value="exportAction_cancel")
	public String cancel(){	
		for(String b:d.getId().split(", ")){
//			System.out.println("b="+b);
			Export c=ds.get(b);
			c.setState(0);
			ds.saveOrUpdate(c);
		}
		return "action_list";
	}
	
	@Action(value="exportAction_getTabledoData")
	public String getTabledoData() throws IOException{	
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		Gson g=new Gson();
		Export e = ds.get(d.getId());
		Set<ExportProduct> s = e.getExportProducts();
		List bs = new ArrayList();
		for(ExportProduct b:s){
			Map map=new HashMap();
			//id, productNo, cnumber, grossWeight, netWeight, sizeLength,
			map.put("id",b.getId());
			map.put("productNo",b.getProductNo());
			map.put("cnumber",b.getCnumber());
			map.put("grossWeight",UtilFuns.convertNull(b.getGrossWeight()));
			map.put("netWeight",UtilFuns.convertNull(b.getNetWeight()));
			map.put("sizeLength",UtilFuns.convertNull(b.getSizeLength()));
			//sizeWidth, sizeHeight, exPrice, tax
			map.put("sizeWidth",UtilFuns.convertNull(b.getSizeWidth()));
			map.put("sizeHeight",UtilFuns.convertNull(b.getSizeHeight()));
			map.put("exPrice",UtilFuns.convertNull(b.getExPrice()));
			map.put("tax",UtilFuns.convertNull(b.getTax()));
			bs.add(map);
		}
		res.getWriter().write(g.toJson(bs));
		return null;
	}
	
	@Action(value="exportAction_exportEws1")
	public String exportEws1() throws Exception_Exception{
		Gson g=new Gson();
		Export e = ds.get(d.getId());
		Map m=new HashMap();
		m.put("exportId",e.getId());
		ArrayList al=new ArrayList();
		Set<ExportProduct> ss = e.getExportProducts();
		for(ExportProduct s:ss){
			Map m1=new HashMap();
			m1.put("exportProductId",s.getId());
			al.add(m1);
		}
		m.put("products",al);		
		System.out.println("m="+m);
		
		String n=ies.exportE(g.toJson(m));
		Map m2=g.fromJson(n,Map.class);
		List<Map> ll=(List<Map>) m2.get("products");
		System.out.println("ll="+ll+"\tm2="+m2);
		e.setState(Integer.parseInt(m2.get("state").toString()));
		for(Map ma:ll){
			Double tax=Double.parseDouble(ma.get("tax").toString());
			ExportProduct xx = eps.get(ma.get("exportProductId").toString());
			xx.setTax(tax);
		}
		ds.saveOrUpdate(e);
		return "action_list";
	}

	
	@Action(value="exportAction_exportEws2")
	public String exportEws2() throws Exception_Exception{
		Export e = ds.get(d.getId());
		
		String s=JSON.toJSONString(e);
		System.out.println("s="+s);
		String n=ies.exportE(s);
		Export m2=JSON.parseObject(n,Export.class);
		System.out.println("m2="+m2);

//		ds.saveOrUpdate(e);
		return "action_list";
	}
	
	@Action(value="exportAction_exportE")
	public String exportErs1() throws Exception_Exception{
		String id1=d.getId();
		Export e1 = ds.get(id1);
		String url="http://localhost:8080/jk_export/ws/export/user";

		WebClient w = WebClient.create(url);
		ExportVo ev = new ExportVo();
		BeanUtils.copyProperties(e1, ev);
		ev.setExportId(e1.getId());
		Set<ExportProduct> e1ps = e1.getExportProducts();
		Set<ExportProductVo> epvs=new HashSet<ExportProductVo>();
		for(ExportProduct e1p:e1ps){
			ExportProductVo epv = new ExportProductVo();
			BeanUtils.copyProperties(e1p,epv);
			epv.setExportProductId(e1p.getId());
			epv.setExportId(e1.getId());
			epvs.add(epv);
		}
		ev.setProducts(epvs);
		w.post(ev);
		
		WebClient w1 = WebClient.create(url+"/"+id1);
		ExportResult exportResult = w1.get(ExportResult.class);
		e1.setState(2);
		Set<ExportProductResult> products = exportResult.getProducts();
		for(ExportProductResult ps:products){
			ExportProduct one = epd.getOne(ps.getExportProductId());
			one.setTax(ps.getTax());
			epd.save(one);
		}
		ds.saveOrUpdate(e1);
		return "action_list";
	}

}
