package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.ModuleDao;
import cn.itcast.domain.Module;
import cn.itcast.service.ModuleService;
import cn.itcast.utils.UtilFuns;

@Component("ms")
public class ModuleServiceImpl implements ModuleService{

	@Autowired
	private ModuleDao dd;
	@Override
	public List<Module> find(Specification<Module> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public Module get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	@RequiresPermissions("模块管理")
	public Page<Module> findPage(Specification<Module> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(Module entity) {
		if(UtilFuns.isEmpty(entity.getId())){
//			entity.setState(1);
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Module> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		Module d1=dd.findOne(id);
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
//		List<Module> ld = dd.findModuleByPid(s);
//		for(Module p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<Module> findModuleByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
