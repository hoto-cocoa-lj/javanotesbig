package cc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import cc.domain.Customer;

@Component("cd")
public class CustomerDaoImpl  implements CustomerDao{
		@Autowired
		private HibernateTemplate  h;
	public List<Customer> a() {
		List<Customer> l = (List<Customer>) h.find("from Customer");
		System.out.println(l);
		return l;
	}

}
