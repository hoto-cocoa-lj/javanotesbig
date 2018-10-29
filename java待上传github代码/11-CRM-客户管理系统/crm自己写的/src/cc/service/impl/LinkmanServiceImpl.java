package cc.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cc.dao.LinkmanDao;
import cc.domain.Customer;
import cc.domain.Linkman;
import cc.service.LinkmanService;
@Component("ls")
@Transactional
public class LinkmanServiceImpl implements LinkmanService{
	@Autowired
private LinkmanDao ld;
	@Override
	public List<Customer> findc() {
		// TODO Auto-generated method stub
		return ld.findc();
	}
	@Override
	public void save(Linkman l) {
		ld.save(l);
		
	}
	@Override
	public List<Linkman> findall() {
		// TODO Auto-generated method stub
		return ld.findall();
	}
	@Override
	public Linkman findbyid(Long lkm_id) {
		// TODO Auto-generated method stub
		return ld.findbyid(lkm_id);
	}
	@Override
	public void update(Linkman l) {
		// TODO Auto-generated method stub
		ld.update(l);
	}
	@Override
	public void delete(Long lkm_id) {
		ld.delete(lkm_id);
		
	}
	@Override
	public List<Linkman> findbydc(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return ld.findbydc(dc);
	}
	@Override
	public int findcountdc(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return ld.findcountdc(dc);
	}
	@Override
	public List<Linkman> findbydcpage(DetachedCriteria dc, int a, int b) {
		// TODO Auto-generated method stub
		return ld.findbydcpage(dc,a,b);
	}

}
