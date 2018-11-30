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
import cn.itcast.dao.ExtCproductDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ExtCproduct;
import cn.itcast.service.ExtCproductService;
import cn.itcast.utils.UtilFuns;

@Component("ecs")
public class ExtCproductServiceImpl implements ExtCproductService{
	@Autowired
	private ContractDao cd;
	@Autowired
	private ExtCproductDao dd;
	@Override
	public List<ExtCproduct> find(Specification<ExtCproduct> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public ExtCproduct get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	public Page<ExtCproduct> findPage(Specification<ExtCproduct> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(ExtCproduct entity) {
//		if(UtilFuns.isEmpty(entity.getId())){
//			Double am=0.0;
//			entity.setId(UUID.randomUUID().toString());
//			if(UtilFuns.isNotEmpty(entity.getPrice())&&UtilFuns.isNotEmpty(entity.getCnumber())){
//				am=entity.getPrice()*entity.getCnumber();		
//			}
//			entity.setAmount(am);
//			Contract c =cd.findOne(entity.getContractProduct().getContract().getId());
//			c.setTotalAmount(c.getTotalAmount()+am);
//			cd.save(c);
//		}else{
//			Double oldd=entity.getAmount();
//			Double am=0.0;
//			if(UtilFuns.isNotEmpty(entity.getPrice())&&UtilFuns.isNotEmpty(entity.getCnumber())){
//				am=entity.getPrice()*entity.getCnumber();
//			}
//			entity.setAmount(am);
//			Contract c =cd.findOne(entity.getContractProduct().getContract().getId());
//			c.setTotalAmount(c.getTotalAmount()+am-oldd);
//			cd.save(c);
//		}
		Double am=0.0;
		Double oldd=entity.getAmount()==null?0.0:entity.getAmount();
		if(UtilFuns.isNotEmpty(entity.getPrice())&&UtilFuns.isNotEmpty(entity.getCnumber())){
			am=entity.getPrice()*entity.getCnumber();		
		}
		if(UtilFuns.isEmpty(entity.getId())){			
			entity.setId(UUID.randomUUID().toString());
			Contract c =cd.findOne(entity.getContractProduct().getContract().getId());
//			c.setFuck(c.getFuck()+1);
			cd.save(c);
		}
		entity.setAmount(am);
		if(oldd!=am){
			Contract c =cd.findOne(entity.getContractProduct().getContract().getId());
			c.setTotalAmount(c.getTotalAmount()+am-oldd);
			cd.save(c);
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExtCproduct> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		ExtCproduct d1=dd.findOne(id);
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
			ExtCproduct d = dd.findOne(id);
			Contract c = cd.findOne(d.getContractProduct().getContract().getId());
			c.setTotalAmount(c.getTotalAmount()-d.getAmount());
			c.setFuck(c.getFuck()-1);
			cd.save(c);
			dd.delete(id);
		}
	}
//	@Override
//	public void dg_delete(String s) {
//		System.out.println("in s="+s);
//		List<ExtCproduct> ld = dd.findExtCproductByPid(s);
//		for(ExtCproduct p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<ExtCproduct> findExtCproductByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
