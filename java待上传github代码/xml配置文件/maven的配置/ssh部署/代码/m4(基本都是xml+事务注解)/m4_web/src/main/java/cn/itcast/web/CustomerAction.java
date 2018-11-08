package cn.itcast.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;



public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
private CustomerService cs;
private  Customer c=new  Customer();
private  List<Customer> lc; 

public List<Customer> getLc() {
	return lc;
}
public void setCs(CustomerService cs) {
	this.cs = cs;
}
public String a(){
	System.out.println("gaga");
	lc=cs.findAll();
	System.out.println(lc);
	return "list";
}
@Override
public Customer getModel() {
	// TODO Auto-generated method stub
	return c;
}

}
