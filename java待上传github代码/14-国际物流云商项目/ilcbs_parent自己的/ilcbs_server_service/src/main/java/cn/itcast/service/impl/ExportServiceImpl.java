package cn.itcast.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.ContractDao;
import cn.itcast.dao.ContractProductDao;
import cn.itcast.dao.ExportDao;
import cn.itcast.dao.FactoryDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.Export;
import cn.itcast.domain.ExportProduct;
import cn.itcast.domain.ExtCproduct;
import cn.itcast.domain.ExtEproduct;
import cn.itcast.service.ExportService;
import cn.itcast.utils.UtilFuns;

@Component("es")
public class ExportServiceImpl implements ExportService{

	@Autowired
	private ExportDao dd;
	@Autowired
	private ContractDao cd;
	@Autowired
	private ContractProductDao cpd;
	@Autowired
	private FactoryDao fd;
	@Override
	public List<Export> find(Specification<Export> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public Export get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}


	@Override
	public Page<Export> findPage(Specification<Export> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	public void saveOrUpdate1(Export entity) {
		// TODO Auto-generated method stub
		if(UtilFuns.isEmpty(entity.getId())){ // 判断修改或者新增
			// 设置默认值 (状态，时间)
			entity.setState(0);  // 0草稿  1已上报
			entity.setInputDate(new Date());
			
			String[] contractIds = entity.getContractIds().split(", ");
			
			StringBuffer sb = new StringBuffer();
			// 需要将contract中的contractNo拼接成字符串放到报运对象的CustomerContract
			for (String cid : contractIds) { //根据id循环所有购销合同
				Contract contract = cd.findOne(cid);
				sb.append(contract.getContractNo()).append(" ");
				// 购销合同使用后，状态应修改为2  0：草稿   1：已上报  2：已报运
				contract.setState(2); //为了防止购销合同多次生成出口报运单，将状态设置为2
			}
			
			entity.setCustomerContract(sb.toString());
			
			
			// 数据搬家  将购销合同下的所有货物转成报运单货物
			// 通过跳跃查询的方式一次性取出所有购销合同下货物的list
			List<ContractProduct> cpList = cpd.findCpByContractIds(contractIds);
			
			HashSet<ExportProduct> exportProducts = new HashSet<ExportProduct>();
			for (ContractProduct contractProduct : cpList) {
				ExportProduct exportProduct = new ExportProduct(); //数据搬家
				exportProduct.setBoxNum(contractProduct.getBoxNum());
				BeanUtils.copyProperties(contractProduct, exportProduct); //数据对拷，用spring的工具类
				
				exportProduct.setId(null); //防止属性对拷后id也对拷
				
				//@OneToMany(mappedBy="export",cascade=CascadeType.ALL) 多方维护一方的关系，需要设置一方对象
				exportProduct.setExport(entity); 
				exportProducts.add(exportProduct);
				
				// 将所有货物下的附件转成报运商品的附件
				Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
				HashSet<ExtEproduct> extEproducts = new HashSet<ExtEproduct>();
				for (ExtCproduct extCproduct : extCproducts) { //购销附件对拷到报运附件里
					ExtEproduct extEproduct = new ExtEproduct();
					BeanUtils.copyProperties(extCproduct, extEproduct);
					
					extEproduct.setId(null); //防止属性对拷后id也对拷
				
					//@OneToMany(mappedBy="exportProduct",cascade=CascadeType.ALL) 多方维护一方的关系，需要设置一方对象
					extEproduct.setExportProduct(exportProduct); 
					extEproducts.add(extEproduct);
				}
				exportProduct.setExtEproducts(extEproducts);
			}
			
			entity.setExportProducts(exportProducts);
		}
		
		dd.save(entity);
	}
	@Override
	public void saveOrUpdate(Export entity) {
		System.out.println("saveOrUpdate entity.getId()="+entity.getId());
		if(UtilFuns.isEmpty(entity.getId())){
			entity.setState(0);
			Date d1 = new Date();
			entity.setCreateTime(d1);
			entity.setId(UUID.randomUUID().toString());		
			String[] ss = entity.getContractIds().split(", ");
			StringBuffer sb=new StringBuffer();
			
			for(String s:ss){
				Contract one = cd.getOne(s);
				sb.append(one.getContractNo()).append(",");
				one.setState(2);
				cd.save(one);
			}
			entity.setCustomerContract(sb.toString());
			
			List<ContractProduct> cpList = cpd.findCpByContractIds(ss);			
			HashSet<ExportProduct> exportProducts = new HashSet<ExportProduct>();
			for(ContractProduct cp:cpList){
				System.out.println("cp="+cp);
				ExportProduct ep=new ExportProduct();
//				ep.setBoxNum(cp.getBoxNum());
				BeanUtils.copyProperties(cp,ep);
//				ep.setId(null);
				ep.setId(UUID.randomUUID().toString());				//=========
				ep.setExport(entity);
				exportProducts.add(ep);
				System.out.println("ep="+ep);
				System.out.println("@@@@"+cp.getId()==ep.getId()+"\t"+cp.getId()+"\t"+ep.getId());
				
				HashSet<ExtEproduct> extEproducts= new HashSet<ExtEproduct>();
				for(ExtCproduct ecp:cp.getExtCproducts()){
					ExtEproduct eep=new ExtEproduct();					
					BeanUtils.copyProperties(ecp,eep);
//					eep.setId(null);
					eep.setId(UUID.randomUUID().toString());		//========
					eep.setExportProduct(ep);
					extEproducts.add(eep);
					System.out.println("eep="+eep);
					System.out.println("%%%%"+ecp.getId()==eep.getId()+"\t"+ecp.getId()+"\t"+eep.getId());
				}				
				ep.setExtEproducts(extEproducts);
			}	
			entity.setExportProducts(exportProducts);
		}else{
			System.out.println(">>>>>>>>>>>>>>>>>>");
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Export> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		Export d1=dd.findOne(id);
		try {		
			if(d1!=null){
				System.out.println("d1="+d1);
				
				dd.delete(id);
			}
		} catch (Exception e) {
		}	
	}

	@Override
	public void delete(String[] ids) {		
		for(String id:ids){

			dd.delete(id);
		}
	}
//	@Override
//	public void dg_delete(String s) {
//		System.out.println("in s="+s);
//		List<Export> ld = dd.findExportByPid(s);
//		for(Export p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<Export> findExportByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}




	
}
