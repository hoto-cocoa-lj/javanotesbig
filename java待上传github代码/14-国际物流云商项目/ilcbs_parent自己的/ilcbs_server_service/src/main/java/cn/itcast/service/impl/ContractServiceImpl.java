package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.itcast.dao.ContractDao;
import cn.itcast.domain.Contract;
import cn.itcast.service.ContractService;
import cn.itcast.utils.UtilFuns;

@Component("cs")
public class ContractServiceImpl implements ContractService{

	@Autowired
	private ContractDao dd;
	@Override
	public List<Contract> find(Specification<Contract> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public Contract get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}

	@Override
	public Page<Contract> findPage(Specification<Contract> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(Contract entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			String uid=UUID.randomUUID().toString();
			entity.setId(uid);
			entity.setState(0);
			entity.setFuck(0);
			entity.setTotalAmount(0.0);
			System.out.println("contractservice entity.id="+entity.getId());
		}	
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Contract> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		Contract d1=dd.findOne(id);
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
//		List<Contract> ld = dd.findContractByPid(s);
//		for(Contract p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<Contract> findContractByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
