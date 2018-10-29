crm第3天教程的示例代码。
可增删改查客户，有分页功能

注意点：
action里使用离线查询进行筛选查询，
jsp/list.jsp里使用了数据回显，注意里面ognl的写法。

以前修改和删除获取customer的id，我自己在action里加了一个属性，
其实不需要，直接cust_id=该id即可，然后在action的c里直接获取。


使用DetachedCriteria dc做分页查询，先分页查再聚合查会报错，原因不明。
先聚合查会改变dc，用dc.setProjection(null)清楚参数后再分页查即可。
还有一个笨方法是在action里设置两个dc分别调用dao方法即可。
		dc.setProjection(Projections.rowCount());
		dc.setProjection(null); // 回归默认语法 select * from 
		hibernateTemplate.findByCriteria(dc, start, pageSize);			//分页查
		
上一页和下一页的设置，可以给它们设置onclick="goto(i)"，i是第几页。然后js部分goto的内容是：
 	 	function goto(i){
			$("input[type=hidden]").val(i);
			$("#form1").submit();
		}
		再在form里放一个隐藏域：<input type=hidden value='1' name='page.a'/>
		
页数下拉选择可以这么写。js部分：
 		$("#select").change(function(){
 			ff($(this).val()); 			
 		})
		select部分：
		h="";
 		for(i=1;i<${page.d}+1;i++){
 			if(i==${page.a}){
 				h+="<option value='"+i+"' selected=true>"+i+"</option>";
 			}else{
 				h+="<option value='"+i+"'>"+i+"</option>";
 		}}
 		$("#select").html(h);
		
		
		
		
		
		
		
bug：http status 404-no result defined for action cc.CustomerAction and result input
原因：默认拦截器发生错误。数据封装出问题了


我的理解是：
	jsp方面的数据，name字段和类name字段（url里类似fuck=you的参数），在action里有常见两种方法获取：
		1：设置private属性和set方法（似乎不设置set方法也没关系）；
		2：action实现modeldriver接口，例如<Customer>，那么name直接用Customer的属性即可。
			遇到外键的情况，name可以用cust_source.dict_id（字段.外键对象的属性）。
			
			
			
	将action的数据传给jsp，用ongl表达式获取。
		普通的private属性用"<s:property value='fuck'/>"（必须设置对应属性的get方法）；
		普通的private对象用"<s:property value='对象.fuck'/>"（必须设置对应属性的get方法）；
		因为普通的private对象只在值栈的list区域，所以不能用#获取。
		
		
		
		下面这个bug原因不清楚，但找到了解决方法。凡是跟回显有关的，全部自己设置private Customer c1和get方法，
		然后在对应的jsp页面value部分全部写c1即可。
		即name用c的属性，c专门用来从jsp获取参数，value用c1.属性，c1专门用来给jsp传输数据。
		action里对应的方法需要写c1=c。
		思路是，action自己创建的c和c1没有任何参数，c从对应的jsp获取参数，然后c1=c使c1获得c的参数。
		所以如果某方法没有从jsp某些参数并且写了c1=c，那么c1回显参数也会失败。具体看UserActionFuck.java。
==========================================================================================		
		Customer的设置外键的属性一定要用"<s:property value='#c.cust_source.dict_id'/>"。
		用"<s:property value='cust_source.dict_id'/>"有时能获取到有时获取不到，bug很难找。
		然而第二天"<s:property value='#c.cust_source.dict_id'/>"就获取不到了，
		用"<s:property value='cust_source.dict_id'/>"才能获取到，原因不明。《这一段有错误》
==========================================================================================			




		Customer的没有设置外键的属性貌似必须用"<s:property value='cust_id'/>"获取，用
		"<s:property value='#c.cust_id'/>"和"<s:property value='c.cust_id'/>"都获取不到。
		(action里是private Customer c，所以这里写#c。)。。。
		
		
		而且有这个现象，需要从数据库获取某个Customer c1对象到页面展示，
		直接在dao层写c=该对象会出现web层debug里值栈无法找到c的其他属性，
		只能在action里写private Customer c1并设置get方法，jsp展示c1数据。原因不明。




下面的代码目的是实现通过筛选获得customer的列表，然后删除某个customer后回到筛选结果页面。
方法是将筛选参数放在c里，删除后继续这个c的筛选参数再次筛选。
可以发现：
	由于action是多实例，使用chain的话后面的action会丢失前面action里的参数。
	所以方法2会失败。
	方法1直接在action内部调用其他action，参数有效；
	另外我在结果展示的jsp里用了上文提到的"<s:property value='cust_source.dict_id'/>"，
	造成参数展示失败但返回值正确的bug，非常难以排查。
		
		
	#方法1
	@Action(value="delete",results={@Result(name="addUI",location="/addUI.jsp")})
	public String delete(){
		Long l=c.getCust_id();
		c=us.findById(l);
		c.setCust_name("");	
		us.delete(l);
		return xs();
	}
	
	#方法2
	@Action(value="delete",results={@Result(location="customer_xs",type="chain")})
	public String delete(){
		Long l=c.getCust_id();
		c=us.findById(l);
		c.setCust_name("");
		us.delete(l);
		return "success";
	}
	
	@Action(value="customer_xs",results={@Result(name="addUI",location="/addUI.jsp")})
	public String xs(){
		System.out.println("test1="+test1);
		DetachedCriteria dc=DetachedCriteria.forClass(Customer.class);
		dc.add(Restrictions.like("cust_name","%"+c.getCust_name()+"%"));

		Long a1 = c.getCust_source().getDict_id();
		Long a2 =c.getCust_level().getDict_id();
		Long a3 =c.getCust_industry().getDict_id();

		dc.add(Restrictions.eq("cust_source.dict_id", a1));
		dc.add(Restrictions.eq("cust_level.dict_id", a2));
		dc.add(Restrictions.eq("cust_industry.dict_id", a3));
		customerList=us.xs(dc);

		return addUI();
	}
		