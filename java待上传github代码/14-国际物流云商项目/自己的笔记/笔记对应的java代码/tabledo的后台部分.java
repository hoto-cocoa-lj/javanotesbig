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
import java.util.List;
import java.util.Map;
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

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.Export;
import cn.itcast.domain.ExportProduct;
import cn.itcast.domain.Factory;
import cn.itcast.domain.MREP;
import cn.itcast.service.ContractService;
import cn.itcast.service.ExportProductService;
import cn.itcast.service.ExportService;
import cn.itcast.service.FactoryService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.Page;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Result(type="redirectAction",name="action_list",location="exportAction_list")
public class ExportAction extends BaseAction<Export> implements ModelDriven<Export>{
	@Autowired
	private ExportService ds;
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


}
