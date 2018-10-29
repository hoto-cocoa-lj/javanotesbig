package cc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cc.domain.Customer;
import cc.domain.Linkman;

public interface LinkmanService {

	List<Customer> findc();

	void save(Linkman l);

	List<Linkman> findall();

	Linkman findbyid(Long lkm_id);

	void update(Linkman l);

	void delete(Long lkm_id);

	List<Linkman> findbydc(DetachedCriteria dc);

	int findcountdc(DetachedCriteria dc);

	List<Linkman> findbydcpage(DetachedCriteria dc, int a, int b);

}
