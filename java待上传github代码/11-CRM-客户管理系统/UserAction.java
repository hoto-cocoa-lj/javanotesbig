package cc.action;


#和addUI.jsp匹配

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cc.domain.BaseDict;
import cc.domain.Customer;

import cc.service.UserService;

@Component("ua")
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")

//全局result
@Result(name="delete",location = "/delete.jsp")
public class UserAction extends ActionSupport implements ModelDriven<Customer>{
	
	private Customer c=new Customer();
	@Autowired
	private UserService us;
	

	private List<BaseDict> l1,l2,l3;
	private List<Customer> customerList;

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public List<BaseDict> getL1() {
		return l1;
	}

	public List<BaseDict> getL2() {
		return l2;
	}

	public List<BaseDict> getL3() {
		return l3;
	}

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return c;
	}
	@Action(value="customer_save",results={@Result(location="customer_findall",type="redirectAction")})
	public String save(){
		us.save(c);
		return "success";
	}
	
	@Action(value="customer_findall",results={@Result(location="/list.jsp")})
	public String findall(){
		customerList=us.findall();
		return "success";
	}
	@Action(value="delete",results={@Result(location="/index.jsp")})
	public String delete(){
		
		
		return "success";
	}
	
	@Action(value="customer_xs",results={@Result(name="addUI",location="/addUI.jsp")})
	public String xs(){
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		dc.add(Restrictions.like("cust_name","%"+c.getCust_name()+"%"));
		dc.add(Restrictions.eq("cust_source.dict_id", c.getCust_source().getDict_id()));
		dc.add(Restrictions.eq("cust_level.dict_id", c.getCust_level().getDict_id()));
		dc.add(Restrictions.eq("cust_industry.dict_id", c.getCust_industry().getDict_id()));
		customerList=us.xs(dc);
//		ActionContext.getContext().getValueStack().push(c);
		return addUI();
//		return "success";
	}
	




	@Action(value="customer_addUI",results={@Result(name="addUI",location="/addUI.jsp")})
	public String addUI(){
		l1=us.findcid("001");
		l2=us.findcid("002");
		l3=us.findcid("006");
		return "addUI";
	}
	
	
	@Action(value="xgUI",results={@Result(name="addUI",location="/xg.jsp")})
	public String xgUI(){
		c = us.findById(c.getCust_id());
		System.out.println("xgUI c="+c);
		return addUI();
	}

}
