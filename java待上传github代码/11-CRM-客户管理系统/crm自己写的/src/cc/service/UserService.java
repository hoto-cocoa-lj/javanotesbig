package cc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cc.domain.BaseDict;
import cc.domain.Customer;


public interface UserService {


void save(Customer c);

List<BaseDict> findcid(String string);

List<Customer> findall();

List<Customer> xs(Customer c);

void delete(Long deletecid);

List<Customer> xs(DetachedCriteria c);

Customer findById(Long deletecid);

void update(Customer c1);
}
