package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.DeptDao;
import cn.itcast.domain.Dept;
import cn.itcast.service.DeptService;
import cn.itcast.utils.UtilFuns;

@Component("ds")
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptDao dd;
	@Override
	public List<Dept> find(Specification<Dept> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public Dept get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	public Page<Dept> findPage(Specification<Dept> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(Dept entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			entity.setState(1);
			dd.save(entity);
		}else{
			dd.save(entity);
		}
	}

	@Override
	public void saveOrUpdateAll(Collection<Dept> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		Dept d1=dd.findOne(id);
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
	@Override
	public void dg_delete(String s) {
		System.out.println("in s="+s);
		List<Dept> ld = dd.findDeptByPid(s);
		for(Dept p:ld){
			dg_delete(p.getId());
		}
		System.out.println("out s="+s);
		deleteById(s);


	}

	
}
