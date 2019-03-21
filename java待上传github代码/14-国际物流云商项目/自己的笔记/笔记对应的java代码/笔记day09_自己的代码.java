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

	@Override
	public void saveOrUpdate(Export entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			entity.setState(0);
			Date d1 = new Date();
			System.out.println("new Date()="+d1);
			entity.setCreateTime(d1);
			entity.setId(UUID.randomUUID().toString());			//======
			String[] ss = entity.getContractIds().replaceAll("\\s+","").split(",");
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
				ExportProduct ep=new ExportProduct();
//				ep.setBoxNum(cp.getBoxNum());
				BeanUtils.copyProperties(cp,ep);
				ep.setId(UUID.randomUUID().toString());				//=========
				ep.setExport(entity);
				exportProducts.add(ep);
				
				HashSet<ExtEproduct> extEproducts= new HashSet<ExtEproduct>();
				for(ExtCproduct ecp:cp.getExtCproducts()){
					ExtEproduct eep=new ExtEproduct();					
					BeanUtils.copyProperties(ecp,eep);
					eep.setId(UUID.randomUUID().toString());
					eep.setExportProduct(ep);
					extEproducts.add(eep);
				}				
				ep.setExtEproducts(extEproducts);
			}			
			entity.setExportProducts(exportProducts);
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
