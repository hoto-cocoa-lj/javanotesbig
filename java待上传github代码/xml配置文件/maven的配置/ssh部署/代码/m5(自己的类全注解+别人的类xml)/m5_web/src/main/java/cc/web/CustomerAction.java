package cc.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cc.domain.Customer;
import cc.service.CustomerService;

@Controller("ca")
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private  List<Customer> lc;
	
	public List<Customer> getLc() {
		return lc;
	}
	@Autowired
	private CustomerService cs;
private Customer c=new Customer();
	public Customer getModel() {
		return c;
	}

	@Action(value="a",results={@Result(location="/index.jsp")})
	public String a(){
		lc=cs.a();
		return "success";
	}
	

}
