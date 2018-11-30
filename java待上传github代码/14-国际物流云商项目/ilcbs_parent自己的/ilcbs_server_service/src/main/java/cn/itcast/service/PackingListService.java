package cn.itcast.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.Contract;
import cn.itcast.domain.PackingList;


public interface PackingListService {
	
	public PackingList get(String id);
	public void save(PackingList a);
	public  Page<PackingList> findPage(Specification<PackingList> spec, Pageable pageable);

	
}
