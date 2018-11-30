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
import cn.itcast.dao.PackingListDao;
import cn.itcast.dao.FactoryDao;
import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;
import cn.itcast.domain.PackingList;
import cn.itcast.domain.ExportProduct;
import cn.itcast.domain.ExtCproduct;
import cn.itcast.domain.ExtEproduct;
import cn.itcast.service.PackingListService;
import cn.itcast.utils.UtilFuns;

@Component("pls")
public class PackingListServiceImpl implements PackingListService{
	@Autowired
	private ExportDao ed;
	@Autowired
	private PackingListDao pld;
	@Override
	public PackingList get(String id) {
		// TODO Auto-generated method stub
		return pld.findOne(id);
	}
	@Override
	public void save(PackingList a) {
		String i=a.getId();
		System.out.println("i="+i);
		String[] exportIds = a.getExportIds().split(", ");
		StringBuffer sb=new StringBuffer();
		for(String s:exportIds){
			sb.append(ed.findOne(s).getContractIds()).append(",");
		}
		a.setExportNos(sb.toString());
		if(pld.findOne(i)==null){
			a.setState(0);			
		}
		pld.save(a);
	}
	@Override
	public Page<PackingList> findPage(Specification<PackingList> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return pld.findAll(spec, pageable);
	}

}
