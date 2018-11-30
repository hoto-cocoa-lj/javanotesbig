package cn.itcast.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import cn.itcast.dao.ContractDao;
import cn.itcast.dao.ContractProductDao;
import cn.itcast.dao.DeptDao;
import cn.itcast.dao.ExportProductDao;
import cn.itcast.dao.ModuleDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.Dept;
import cn.itcast.domain.Export;
import cn.itcast.domain.ExportProduct;
import cn.itcast.domain.ExportProductResult;
import cn.itcast.domain.ExportProductVo;
import cn.itcast.domain.ExportResult;
import cn.itcast.domain.ExportVo;
import cn.itcast.domain.Module;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.export.webservice.Exception_Exception;
import cn.itcast.export.webservice.IEpService;
import cn.itcast.service.DeptService;
import cn.itcast.service.ExportService;
import cn.itcast.service.ModuleService;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.HttpClientUtil;
import cn.itcast.utils.MailUtil;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.cargo.OutProductAction;
import cn.itcast.web.action.stat.StatCharAction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JpaTest2 {
	@Autowired
	private IEpService ies;
	@Autowired
	private ExportService es;
	@Autowired
	private StatCharAction sca;
	@Autowired
	private DeptDao dd;
	@Autowired
	private DeptService  ds;
	@Autowired
	private UserDao ud;
	@Autowired
	private UserService  us;
	@Autowired
	private RoleDao rd;
	@Autowired
	private RoleService  rs;
	@Autowired
	private ModuleDao md;
	@Autowired
	private ModuleService ms;
	@Autowired
	private ContractDao cd;
	@Autowired
	private ContractProductDao cpd;
	@Autowired
	private ExportProductDao epd;

	public void t11(List<Contract> list,String inputDate) throws FileNotFoundException, IOException, ParseException{		
//		String f1="/webapp/make/xlsprint/tOUTPRODUCT.xlsx".replace("/",File.separator);
//		System.out.println("f1="+f1);
//		String file=ServletActionContext.getServletContext().getRealPath(f1);
		String file="e:/1.xlsx";
		InputStream s=new FileInputStream(file);
		XSSFWorkbook b=new XSSFWorkbook(s);
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
		        cr.createCell(4).setCellValue(p.getCnumber());
		        cr.createCell(5).setCellValue(p.getFactoryName());
		        cr.createCell(6).setCellValue(UtilFuns.dateTimeFormat(c.getDeliveryPeriod()));
		        cr.createCell(7).setCellValue(UtilFuns.dateTimeFormat(c.getShipTime()));
		        cr.createCell(8).setCellValue(c.getTradeTerms());
	        }    	
	    }   
	    String a3=inputDate+".xlsx";
	    b.write(new FileOutputStream("e:/"+a3));	 
//	    ByteArrayOutputStream os = new ByteArrayOutputStream();
//	    b.write(os);
//	    HttpServletResponse a2 = ServletActionContext.getResponse();
//	    new DownloadUtil().download(os, a2,a3);
	}
@Test
public void t1() throws FileNotFoundException, IOException{		
	InputStream s=new FileInputStream("e:/1.xlsx");
	XSSFWorkbook b=new XSSFWorkbook(s);
	Sheet s1 = b.getSheetAt(0);
    int lr = s1.getLastRowNum()+1;        
    while(lr<10){
    	int lc=1;
        Row cr = s1.createRow(lr++);
        while(lc<5){
        	System.out.println(lr+":"+lc+":"+cr);
	        Cell cc = cr.createCell(lc);
	        cc.setCellValue(lr+":"+lc);
	        lc++;
        }
    }
    b.write(new FileOutputStream("e:/2.xlsx"));
}

@Test
public void t2() throws FileNotFoundException, IOException, ParseException{
	String inputDate="2018-11";
	List<Contract> list = cd.getbyship(inputDate);
	System.out.println(System.getProperty("user.dir"));
	t11(list,inputDate);
}
@Test
public void t3() throws ParseException{
	Contract mc = cd.findOne("40289181672ffaba01672ffdcbbe0000");
	Date d = mc.getShipTime();
	String pattern="yyyy-MM-dd";
	SimpleDateFormat s=new SimpleDateFormat(pattern);
	String a = s.format(d);
	String b = UtilFuns.dateTimeFormat(d);
	System.out.println("d="+d+"\ta="+a+"\tb="+b);

}
@Test
public void t4(){
	List<Object[]> g = cpd.getgb();
	for(Object[] x:g){
		System.out.println(x[0]+"\t"+x[1]);
	}
}
@Test
public void t5() throws IOException{
	List a=new ArrayList();
	List<Object[]> x = cpd.getgb();
	Gson g=new Gson();
	for(Object[] y:x){
		Map m=new HashMap();
		System.out.println(y[0]+"\t"+y[1]);
		
		m.put("公司",(String)y[0]);
		m.put("销量",y[1].toString());
		a.add(m);
	}
	System.out.println(a);
	System.out.println(g.toJson(a));
	}
@Test
public void online1(){
	List<Object[]> x = cpd.getip();
	Map m=new HashMap();
	List<String> l1=new ArrayList<String>();
	List<Integer> l2=new ArrayList<Integer>();
	for(Object[] y:x){
		l1.add((String) y[0]);
		l2.add(Integer.parseInt(y[1].toString()));
	}
	m.put("time",l1);
	m.put("count",l2);
	System.out.println(m);

}
@Test
public void exportE1() throws Exception_Exception{
	Gson g=new Gson();
	Export e = es.get("4028a08167545b720167545cb9fc0000");	
	System.out.println("e="+e.getId());
	System.out.println("\t"+e.getExportProducts());
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
	String m1 = g.toJson(m);
	System.out.println("m1="+m1);

	String n=ies.exportE(m1);
	Map m2=g.fromJson(n,Map.class);
	List<Map> ll=(List<Map>) m2.get("products");
	System.out.println("ll="+ll+"\tm2="+m2);
	e.setState(Integer.parseInt(m2.get("state").toString()));
	for(Map ma:ll){
		System.out.println(ma.get("tax").toString());
	}
}
@Test
public void exportErs1(){
	String id1="4028a08167545b720167545cb9fc0000";
	Export e1 = es.get(id1);
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
}
@Test
public void exportErs2(){	
	String id="4028a08167545b720167545cb9fc0000";
	String url="http://localhost:8080/jk_export/ws/export/user";
	HttpClientUtil.doGet(url+"/"+id);
}


}


