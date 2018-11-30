package cn.itcast.web.action.stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;


import cn.itcast.dao.ContractDao;
import cn.itcast.dao.ContractProductDao;
import cn.itcast.domain.BaseEntity;

@Component("test1")
@Namespace("/stat")
public class StatCharAction extends BaseEntity{
	@Autowired
	private ContractProductDao cpd;
	
	@Action(value="statChartAction_factorysale",results=@Result(name="factorysale",
			location="/WEB-INF/pages/stat/chart/pie3d.jsp"))
	public String factorysale(){
		return "factorysale";
	}
	
	@Action(value="statChartAction_factorysale1")
	public String factorysale1() throws IOException{
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		Gson g=new Gson();
		List a=new ArrayList();
		List<Object[]> x = cpd.getgb();
		for(Object[] y:x){
			if(y[0]!=null&&y[1]!=null){
				Map m=new HashMap();
				System.out.println(y[0]+"\t"+y[1]);
				m.put("fac",y[0]);
				m.put("mon",y[1]);
				a.add(m);
			}
		}
		System.out.println(a);
		res.getWriter().write(g.toJson(a));
		return null;
	}
	
	@Action(value="statChartAction_productsale",results=@Result(name="productsale",
			location="/WEB-INF/pages/stat/chart/c3d.jsp"))
	public String productsale(){
		return "productsale";
	}
	
	@Action(value="statChartAction_c3d1")
	public String line() throws IOException{
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		Gson g=new Gson();
		List a=new ArrayList();
		List<Object[]> x = cpd.getline();
		for(Object[] y:x){
				if(y[0]!=null&&y[1]!=null){
				Map m=new HashMap();
				System.out.println(y[0]+"\t"+y[1]);
				m.put("fac",y[0]);
				m.put("mon",y[1]);
				a.add(m);
			}
		}
		System.out.println(a);
		res.getWriter().write(g.toJson(a));
		return null;
	}
	
	@Action(value="statChartAction_onlineinfo",results=@Result(name="onlineinfo",
			location="/WEB-INF/pages/stat/chart/line.jsp"))
	public String onlineinfo(){
		return "onlineinfo";
	}
	
	@Action(value="statChartAction_line1")
	public String online() throws IOException{
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		Gson g=new Gson();

		List<Object[]> x = cpd.getip();
		Map m=new HashMap();
		List<String> l1=new ArrayList<String>();
		List<Integer> l2=new ArrayList<Integer>();
		for(Object[] y:x){
			l1.add((String) y[0]);
			l2.add(Integer.parseInt(y[1].toString()));
		}
		m.put("time",l1);m.put("count",l2);
		System.out.println(m);
		res.getWriter().write(g.toJson(m));
		return null;
	}
}
