package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.ContractDao;
import cn.itcast.dao.ExportProductDao;
import cn.itcast.dao.FactoryDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ExportProduct;
import cn.itcast.domain.ExtCproduct;
import cn.itcast.service.ExportProductService;
import cn.itcast.utils.UtilFuns;

@Component("eps")
public class ExportProductServiceImpl implements ExportProductService{

	@Autowired
	private ExportProductDao dd;
	@Autowired
	private ContractDao cd;
	@Autowired
	private FactoryDao fd;
	@Override
	public List<ExportProduct> find(Specification<ExportProduct> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public ExportProduct get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}


	@Override
	public Page<ExportProduct> findPage(Specification<ExportProduct> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(ExportProduct entity) {
		if(UtilFuns.isEmpty(entity.getId())){
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExportProduct> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		ExportProduct d1=dd.findOne(id);
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
//		List<ExportProduct> ld = dd.findExportProductByPid(s);
//		for(ExportProduct p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<ExportProduct> findExportProductByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}




	
}
