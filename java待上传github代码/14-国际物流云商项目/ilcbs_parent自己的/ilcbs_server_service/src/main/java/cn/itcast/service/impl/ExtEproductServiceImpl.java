package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;

import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.ContractDao;
import cn.itcast.dao.ExtEproductDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ExtEproduct;
import cn.itcast.service.ExtEproductService;
import cn.itcast.utils.UtilFuns;

@Component("ees")
public class ExtEproductServiceImpl implements ExtEproductService{
	@Autowired
	private ContractDao cd;
	@Autowired
	private ExtEproductDao dd;
	@Override
	public List<ExtEproduct> find(Specification<ExtEproduct> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public ExtEproduct get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	public Page<ExtEproduct> findPage(Specification<ExtEproduct> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(ExtEproduct entity) {
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExtEproduct> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		ExtEproduct d1=dd.findOne(id);
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
//		List<ExtEproduct> ld = dd.findExtEproductByPid(s);
//		for(ExtEproduct p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<ExtEproduct> findExtEproductByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
