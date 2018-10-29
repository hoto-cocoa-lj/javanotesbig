package cc.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import cc.dao.LinkmanDao;
import cc.domain.Customer;
import cc.domain.Linkman;
@Component("ld")
public class LinkmanDaoImpl implements LinkmanDao {
	@Autowired
	@Qualifier("hibernateTemplate")
private HibernateTemplate h;
	@Override
	public List<Customer> findc() {
		// TODO Auto-generated method stub
		return (List<Customer>) h.find("from Customer");
	}
	@Override
	public void save(Linkman l) {
		h.save(l);		
	}
	@Override
	public List<Linkman> findall() {
		// TODO Auto-generated method stub
		return (List<Linkman>) h.find("from Linkman");
	}
	@Override
	public Linkman findbyid(Long lkm_id) {
		// TODO Auto-generated method stub
		return h.get(Linkman.class, lkm_id);
	}
	@Override
	public void update(Linkman l) {
		// TODO Auto-generated method stub
		h.update(l);
	}
	@Override
	public void delete(Long lkm_id) {
		h.delete(findbyid(lkm_id));
		
	}
	@Override
	public List<Linkman> findbydc(DetachedCriteria dc) {
		return (List<Linkman>) h.findByCriteria(dc);
	}
	@Override
	public int findcountdc(DetachedCriteria dc) {
		dc.setProjection(Projections.rowCount());
		System.out.println("findcountdc dc="+dc);
		List<Long> l= (List<Long>) h.findByCriteria(dc);
		System.out.println("findcountdc l="+l);
		return l.get(0).intValue();
	}
	@Override
	public List<Linkman> findbydcpage(DetachedCriteria dc, int a, int b) {
		dc.setProjection(null);
		return (List<Linkman>) h.findByCriteria(dc,a*b-b,b);
	}

}
