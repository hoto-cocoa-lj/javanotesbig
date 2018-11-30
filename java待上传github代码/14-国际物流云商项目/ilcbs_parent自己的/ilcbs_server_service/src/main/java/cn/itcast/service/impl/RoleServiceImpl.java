package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.RoleDao;
import cn.itcast.domain.Role;
import cn.itcast.service.RoleService;
import cn.itcast.utils.UtilFuns;

@Component("rs")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao dd;
	@Override
	public List<Role> find(Specification<Role> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public Role get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	public Page<Role> findPage(Specification<Role> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(Role entity) {
		if(UtilFuns.isEmpty(entity.getId())){
//			entity.setState(1);
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Role> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		Role d1=dd.findOne(id);
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
//		List<Role> ld = dd.findRoleByPid(s);
//		for(Role p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<Role> findRoleByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
