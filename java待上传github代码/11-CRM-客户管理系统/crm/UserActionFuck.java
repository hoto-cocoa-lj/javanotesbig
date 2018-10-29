package cc.action;




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
public class UserAction extends ActionSupport implements ModelDriven<Customer>{
	
	private Customer c=new Customer();
	private Customer c1;
	
	public Customer getC1() {
		return c1;
	}


	@Autowired
	private UserService us;
	private String test1;
	
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
	@Action(value="delete",results={@Result(name="addUI",location="/addUI.jsp")})
	public String delete(){
		Long l=c.getCust_id();
		c=us.findById(l);
		
		c.setCust_name("");
		Long a1 = c.getCust_source().getDict_id();
		Long a2 =c.getCust_level().getDict_id();
		Long a3 =c.getCust_industry().getDict_id();
		System.out.println("delete::"+a1+"\t"+a2+"\t"+a3);		
		us.delete(l);
		return xs();
	}
	
	@Action(value="customer_xs",results={@Result(name="addUI",location="/addUI.jsp")})
	public String xs(){
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		dc.add(Restrictions.like("cust_name","%"+c.getCust_name()+"%"));

		Long a1 = c.getCust_source().getDict_id();
		Long a2 =c.getCust_level().getDict_id();
		Long a3 =c.getCust_industry().getDict_id();
		System.out.println("xs::"+a1+"\t"+a2+"\t"+a3);
		dc.add(Restrictions.eq("cust_source.dict_id", a1));
		dc.add(Restrictions.eq("cust_level.dict_id", a2));
		dc.add(Restrictions.eq("cust_industry.dict_id", a3));
		customerList=us.xs(dc);c1=c;
		System.out.println("customerList="+customerList+"\tdc"+dc);
		return addUI();
	}
	




	@Action(value="customer_addUI",results={@Result(name="addUI",location="/addUI.jsp")})
	public String addUI(){
		l1=us.findcid("001");
		l2=us.findcid("002");
		l3=us.findcid("006");
		
		System.out.println("addui de l123 ::"+l1.size()+"\t"+l2.size()+"\t"+l3.size());
		try {
			Long a1 = c1.getCust_source().getDict_id();
			Long a2 =c1.getCust_level().getDict_id();
			Long a3 =c1.getCust_industry().getDict_id();
			System.out.println("adduiµÄc1µÄid::"+a1+"\t"+a2+"\t"+a3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "addUI";
	}
	
	
	@Action(value="xgUI",results={@Result(name="addUI",location="/xg.jsp")})
	public String xgUI(){
		
		c1= us.findById(c.getCust_id());
		return addUI();
	}
	
	@Action(value="customer_xg",results={@Result(name="addUI",location="/xg.jsp")})
	public String xg(){
		us.update(c);
		c1=c;
		return addUI();
	}
}
