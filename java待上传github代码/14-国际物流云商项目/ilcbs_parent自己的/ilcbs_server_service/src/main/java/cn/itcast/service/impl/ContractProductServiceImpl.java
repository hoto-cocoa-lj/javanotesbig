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
import cn.itcast.dao.ContractProductDao;
import cn.itcast.dao.FactoryDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.ExtCproduct;
import cn.itcast.service.ContractProductService;
import cn.itcast.utils.UtilFuns;

@Component("cps")
public class ContractProductServiceImpl implements ContractProductService{

	@Autowired
	private ContractProductDao dd;
	@Autowired
	private ContractDao cd;
	@Autowired
	private FactoryDao fd;
	@Override
	public List<ContractProduct> find(Specification<ContractProduct> spec) {
		// TODO Auto-generated method stub
		return dd.findAll(spec);
	}

	@Override
	public ContractProduct get(String id) {
		// TODO Auto-generated method stub
		return dd.findOne(id);
	}


	@Override
	public Page<ContractProduct> findPage(Specification<ContractProduct> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return dd.findAll(spec, pageable);
	}

	@Override
	public void saveOrUpdate(ContractProduct entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			entity.setId(UUID.randomUUID().toString());
			Double am=0.0;
			if(UtilFuns.isNotEmpty(entity.getPrice())&&UtilFuns.isNotEmpty(entity.getCnumber())){
				am+=entity.getPrice()*entity.getCnumber();
			}
			entity.setAmount(am);
//			entity.setFactory(fd.findOne(entity.getFactory().getId()));
			Contract c = cd.findOne(entity.getContract().getId());
			c.setTotalAmount(c.getTotalAmount()+am);
			cd.save(c);
//			entity.setContract(c);
//			entity.setState(1);
		}else{
			Double oldd=entity.getAmount();
			Double am=0.0;
			if(UtilFuns.isNotEmpty(entity.getPrice())&&UtilFuns.isNotEmpty(entity.getCnumber())){
				am+=entity.getPrice()*entity.getCnumber();
			}
			if(am!=oldd){
				entity.setAmount(am);
				Contract c = cd.findOne(entity.getContract().getId());
				c.setTotalAmount(c.getTotalAmount()+am-oldd);
				cd.save(c);
			}
		}
		dd.save(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ContractProduct> entities) {
		dd.save(entities);
		
	}

	@Override
	public void deleteById(String id) {
		ContractProduct d1=dd.findOne(id);
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
			ContractProduct d = dd.findOne(id);
			Contract c = d.getContract();
			Double nm=c.getTotalAmount();
			nm=nm-d.getAmount();
			c.setFuck(c.getFuck()-d.getExtCproducts().size());
			for(ExtCproduct e:d.getExtCproducts()){
				nm-=e.getAmount();
			}
			c.setTotalAmount(nm);
			cd.save(c);
			dd.delete(id);
		}
	}
//	@Override
//	public void dg_delete(String s) {
//		System.out.println("in s="+s);
//		List<ContractProduct> ld = dd.findContractProductByPid(s);
//		for(ContractProduct p:ld){
//			dg_delete(p.getId());
//		}
//		System.out.println("out s="+s);
//		deleteById(s);
//
//
//	}

	@Override
	public List<ContractProduct> findContractProductByNameLike(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractProduct> getCid(String cid) {
		// TODO Auto-generated method stub
		return dd.findByContractId(cid);
	}



	
}
