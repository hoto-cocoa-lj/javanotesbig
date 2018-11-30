package cn.itcast.web.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.dao.ContractDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.Factory;
import cn.itcast.service.FactoryService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.Page;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;


@Namespace("/cargo")
public class OutProductAction{
	@Autowired
	private ContractDao cd;
	private String inputDate;
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	@Action(value="outProductAction_toedit",results={@Result(name="toedit",
				location="/WEB-INF/pages/cargo/outproduct/jOutProduct.jsp")})
	public String toedit() {
		// TODO Auto-generated method stub
		return "toedit";
	}
	@Action(value="outProductAction_print")
	public String print() throws IOException, ParseException {
//		System.out.println("inputDate="+inputDate);
//		inputDate="201608";
		List<Contract> list = cd.getbyship(inputDate);
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
		        cr.createCell(4).setCellValue(p.getCnumber());
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

