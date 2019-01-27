package cn.itcast.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.serviceimpl.CustomerServiceImpl;

@ParentPackage("struts-default")
@Namespace("/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>
{
	private Customer customer=new Customer();
	@Override
	public Customer getModel() {
		// 需要你给它一个对象 他要用这个对象封装数据
		return customer;
	}
	// property name
	/*private List<Customer> list;
	public List<Customer> getList() {
		return list;
	}*/

	// 查询所有客户
	@Action(value="customer_findAll",results={@Result(name="success",location="/jsp/customer/list.jsp")})
	public String findAll()
	{
		// 调用service
		CustomerService customerService = new CustomerServiceImpl();
		// 所有客户数据(很多)
		List<Customer> list = customerService.findAll();
		// 放在域中
				// struts2有没有提供获取到request对象的方法
		/*HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);*/
		
		// 存在值栈中  放在成员位置上    push放在栈顶
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.push(list);
		return "success";
	}
	
	// 跳转到添加页面
	@Action(value="customer_addUI",results={@Result(name="addUI",location="/jsp/customer/add.jsp")})
	public String addUI(){
	
		return "addUI";
	}
	
	// 保存客户
	@Action(value="customer_add",results={@Result(name="toaction",type="redirectAction",location="customer_findAll")})
	public String add()
	{
		CustomerService customerService = new CustomerServiceImpl();
		customerService.save(customer);
		// 还得查询最新数据 到list显示最新数据
		return "toaction";
	}

	
}
