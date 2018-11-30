package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.FactoryDao;
import cn.itcast.domain.Factory;
import cn.itcast.service.FactoryService;
import cn.itcast.utils.UtilFuns;

@Component("fs")
public class FactoryServiceImpl implements FactoryService{

	@Autowired
	private FactoryDao dd;
	@Override
	public List<Factory> find(Specification<Factory> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public Factory get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	public Page<Factory> findPage(Specification<Factory> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(Factory entity) {
		if(UtilFuns.isEmpty(entity.getId())){
//			entity.setState(1);
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Factory> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		Factory d1=dd.findOne(id);
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
//		List<Factory> ld = dd.findFactoryByPid(s);
//		for(Factory p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<Factory> findFactoryByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
