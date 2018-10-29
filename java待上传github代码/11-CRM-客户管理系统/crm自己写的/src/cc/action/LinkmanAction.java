package cc.action;




import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.Criterion;
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
import cc.domain.Linkman;
import cc.domain.Page;
import cc.service.LinkmanService;
import cc.service.UserService;
@Component("ual")
@Scope("prototype")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman>{
	
	private Linkman l=new  Linkman();
	private Linkman l1;
	private List<Customer> lc;
	private List<Linkman> ll;
	private Page page=new Page();
	private int b=page.getB();
	
	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	public List<Linkman> getLl() {
		return ll;
	}

	public  Linkman getL1() {
		return l1;
	}

	public List<Customer> getLc() {
		return lc;
	}

	@Autowired
	private LinkmanService ls;

	@Override
	public Linkman getModel() {
		// TODO Auto-generated method stub
		return l;
	}
	
	@Action(value="linkman_addUI",results={@Result(name="addUI",location="/addUI2.jsp")})
	public String addUI(){
		lc=ls.findc();
//		System.out.println(lc.get(0).getLinkmans());
		return "addUI";
	}

	@Action(value="linkman_save",results={@Result(name="addUI",location="linkman_addUI",type="redirectAction")})
	public String save(){
		System.out.println("save l="+l);
		System.out.println("save l.getCustomer().getCust_id()="+l.getCustomer().getCust_id());
		ls.save(l);
		return "addUI";
	}
	@Action(value="linkman_findall",results={@Result(name="addUI",location="/list2.jsp")})
	public String findall(){
		DetachedCriteria dc=DetachedCriteria.forClass(Linkman.class);
		if(l.getLkm_name()!=null){
			dc.add(Restrictions.like("lkm_name","%"+l.getLkm_name()+"%"));
			if(l.getCustomer().getCust_id()!=-1){
				dc.add(Restrictions.eq("customer.cust_id",l.getCustomer().getCust_id()));
			}	
		}		
		l1=l;	
		if(page.getA()<1){
			page.setA(1);
		}
		System.out.println(page);
		
		int i=ls.findcountdc(dc);
		
		
		ll=ls.findbydcpage(dc,page.getA(),page.getB());
		page.setC(i);

		lc=ls.findc();
		System.out.println("i="+i+ll);
		return "addUI";
	}
	@Action(value="linkman_xs",
			results={@Result(name="addUI",location="linkman_findall",type="chain")})
	public String linkman_xs(){
		return "addUI";
	}
	
	@Action(value="linkman_xgUI",results={@Result(name="addUI",location="/xg2.jsp")})
	public String xgUI(){
		l1=ls.findbyid(l.getLkm_id());
		lc=ls.findc();
		System.out.println("linkman_xgUI l1="+l1);
		return "addUI";
	}
	
	@Action(value="linkman_xg",results={@Result(name="addUI",location="/xg2.jsp")})
	public String linkman_xg(){
		ls.update(l);
		lc=ls.findc();
		l1=l;
		return "addUI";
	}
	
	@Action(value="linkman_delete",results={@Result(name="addUI",location="linkman_findall",type="redirectAction")})
	public String linkman_delete(){
		ls.delete(l.getLkm_id());
		return "addUI";
	}
	
//	@Action(value="linkman_xs",results={@Result(name="addUI",location="/list2.jsp")})
//	public String linkman_xs(){
//		DetachedCriteria dc=DetachedCriteria.forClass(Linkman.class);
//		dc.add(Restrictions.like("lkm_name","%"+l.getLkm_name()+"%"));
//		if(l.getCustomer().getCust_id()!=-1){
//			dc.add(Restrictions.eq("customer.cust_id",l.getCustomer().getCust_id()));
//		}
//		l1=l;
//		lc=ls.findc();
//		ll=ls.findbydc(dc);
//		System.out.println("linkman_xs ll="+ll+"\tdc="+dc);
//		return "addUI";
//	}
}