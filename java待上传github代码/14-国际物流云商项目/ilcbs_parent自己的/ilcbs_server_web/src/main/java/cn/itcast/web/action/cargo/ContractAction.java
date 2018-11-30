package cn.itcast.web.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.dao.ContractProductDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.User;
import cn.itcast.service.ContractService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.Page;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Result(type="redirectAction",name ="action_list",location="contractAction_list")
public class ContractAction extends BaseAction<Contract> implements ModelDriven<Contract>{
	@Autowired
	private ContractService ds;
	@Autowired
	private ContractProductDao cpd;
	private Contract d=new Contract();
	private 		// contractList,0停用的就不要了
	Specification<Contract> spec = new Specification<Contract>() {

		// root:取当前对象的属性.as(属性中的类型) query：order，group cb:or and equles from
		// Contract where state = 1
		@Override
		public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			// TODO Auto-generated method stub
			return cb.equal(root.get("state").as(Integer.class), 1);
		}
	};


	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public Contract getModel() {
		// TODO Auto-generated method stub
		return d;
	}
	
	@Action(value="contractAction_list",results={@Result(name="list",location=
			"/WEB-INF/pages/cargo/contract/jContractList.jsp")})
	public String list() {
		final User u = (User) session.get("_CURRENT_USER");
		Specification<Contract> p = new Specification<Contract>() {
			@Override
			public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1=null;
				Integer cr = u.getUserinfo().getDegree();
				if(cr==4){
					p1=cb.equal(root.get("createBy").as(String.class), u.getId());
				}else if(cr==3){
					p1=cb.equal(root.get("createDept").as(String.class), u.getDept().getId());
				}else if(cr==2){
					
				}else if(cr==1){
					
				}else{
				}			
				return  p1;
			}
		};
		org.springframework.data.domain.Page<Contract> page2 = ds.
				findPage(p, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
		page.setResults(page2.getContent());
		page.setUrl("contractAction_list");
		super.push(page);
		return "list";
	}
	
	@Action(value="contractAction_toview",results={@Result(name="toview",location=
			"/WEB-INF/pages/cargo/contract/jContractView.jsp")})	
	public String toview(){
		d=ds.get(d.getId());
		super.push(d);
		return "toview";
	}
		
	@Action(value="contractAction_tocreate",results={@Result(name="tocreate",location=
			"/WEB-INF/pages/cargo/contract/jContractCreate.jsp")})	
	public String tocreate(){
//		List<Contract> contractList=ds.find(spec);
//		super.put("contractList",contractList);
		return "tocreate";
	}
	
	@Action(value="contractAction_insert")
	public String insert(){
		User u = (User) session.get("_CURRENT_USER");
		d.setCreateBy(u.getId());
		d.setCreateDept(u.getDept().getId());
		ds.saveOrUpdate(d);
		return "action_list";
	}
	
	@Action(value="contractAction_toupdate",results={@Result(name="toupdate",location=
			"/WEB-INF/pages/cargo/contract/jContractUpdate.jsp")})	
	public String toupdate(){
		super.push(ds.get(d.getId()));
		return "toupdate";
	}
	
	@Action(value="contractAction_update")
	public String update(){
		Contract d1=ds.get(d.getId());
		d1.setCustomName(d.getCustomName());
		d1.setPrintStyle(d.getPrintStyle());
		d1.setContractNo(d.getContractNo());
		d1.setOfferor(d.getOfferor());
		d1.setInputBy(d.getInputBy());
		d1.setCheckBy(d.getCheckBy());
		d1.setInspector(d.getInspector());
		d1.setSigningDate(d.getSigningDate());
		d1.setImportNum(d.getImportNum());
		d1.setShipTime(d.getShipTime());
		d1.setTradeTerms(d.getTradeTerms());
		d1.setDeliveryPeriod(d.getDeliveryPeriod());
		d1.setCrequest(d.getCrequest());
		d1.setRemark(d.getRemark());
		System.out.println("xiugai d1="+d1);
		ds.saveOrUpdate(d1);
		return "action_list";
	}
	@Action(value="contractAction_delete")
	public String delete(){	
		String a=d.getId().replaceAll("\\s+","");
//		System.out.println("a="+a);
		ds.delete(a.split(","));
		return "action_list";
	}
	@Action(value="contractAction_submit")
	public String submit(){	
		String a=d.getId().replaceAll("\\s+","");
//		System.out.println("a="+a);
		for(String b:a.split(",")){
//			System.out.println("b="+b);
			Contract c=ds.get(b);
			c.setState(1);
			ds.saveOrUpdate(c);
		}
		return "action_list";
	}
	@Action(value="contractAction_cancel")
	public String cancel(){	
		String a=d.getId().replaceAll("\\s+","");
//		System.out.println("a="+a);
		for(String b:a.split(",")){
//			System.out.println("b="+b);
			Contract c=ds.get(b);
			c.setState(0);
			ds.saveOrUpdate(c);
		}
		return "action_list";
	}
	
	@Action(value="contractAction_print")
	public String print() throws Exception {
		HttpServletResponse res = ServletActionContext.getResponse();
		Contract c = ds.get(d.getId());
		List<ContractProduct> cplist = cpd.findCpList(c.getId());
		String p=ServletActionContext.getServletContext().getRealPath("/");
		new ContractPrint().print(c,cplist, p, res);
		return null;
	}
	
	@Action(value="contractAction_print1")
	public String print1() throws IOException, ParseException {
//		System.out.println("inputDate="+inputDate);
		String[] ss = d.getId().replaceAll("\\s+","").split(",");
		List<Contract> list =  new ArrayList<Contract>();
		for(String s:ss){
			list.add(ds.get(s));
		}
		String inputDate="FUCK-U";	
		System.out.println("\tlist="+list);
		t1(list,inputDate);
		return null;
	}

	public void t1(List<Contract> list,String inputDate) throws FileNotFoundException, IOException, ParseException{		
		String f1="/make/xlsprint/tOUTPRODUCT.xlsx".replace("/",File.separator);
		String file=ServletActionContext.getServletContext().getRealPath(f1);
		InputStream s=new FileInputStream(file);
		Workbook b=new XSSFWorkbook(s);
		Sheet s1 = b.getSheetAt(0);
		String[] st = inputDate.replace("-0", "-").split("-");
		String sn=st[0]+"年"+st[1]+"月出货单";
		s1.getRow(0).getCell(1).setCellValue(sn);
	    int lr = s1.getLastRowNum()+1;         
	    for(Contract c:list){
	    	Set<ContractProduct> ps = c.getContractProducts();
	    	for(ContractProduct p:ps){
		        Row cr = s1.createRow(lr++);
		        cr.createCell(1).setCellValue(c.getCustomName());
		        cr.createCell(2).setCellValue(c.getContractNo());
		        cr.createCell(3).setCellValue(p.getProductNo());
				Integer	 fu= p.getCnumber();
				if(fu==null) {
					fu=0;
				}
//				System.out.println("fu="+fu);
		        cr.createCell(4).setCellValue(fu);
		        cr.createCell(5).setCellValue(p.getFactoryName());
		        cr.createCell(6).setCellValue(UtilFuns.dateTimeFormat(c.getDeliveryPeriod()));
		        cr.createCell(7).setCellValue(UtilFuns.dateTimeFormat(c.getShipTime()));
		        cr.createCell(8).setCellValue(c.getTradeTerms());
	        }    	
	    }
//	    b.write(new FileOutputStream("e:/2.xlsx"));
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    b.write(os);
	    HttpServletResponse a2 = ServletActionContext.getResponse();
	    String a3=sn+".xlsx";
	    new DownloadUtil().download(os, a2,a3);
	}
}
