package cc.action;




import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cc.domain.BaseDict;
import cc.domain.Customer;

import cc.service.UserService;
@Component("ua")
public class UserAction extends ActionSupport implements ModelDriven<Customer>{
	private Customer c=new Customer();
	@Autowired
	private UserService us;
	
	private List<BaseDict> l1,l2,l3;

	
	//有get方法就可以将数据放在值栈并在jsp里用ognl展示
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
	public String save(){
		System.out.println(c);
		us.save(c);
		return null;
	}
	@Action(value="customer_addUI",results={@Result(name="addUI",location="/addUI.jsp")})
	public String addUI(){
		l1=us.findcid("001");
		l2=us.findcid("002");
		l3=us.findcid("006");
		System.out.println(l1);
		return "addUI";
	}

}
