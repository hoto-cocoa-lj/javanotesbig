<%@ page language="java" import="java.util.*" pageEncoding="UTF8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
  </head>
  
  <body>
  
   <form action="${pageContext.request.contextPath}/linkman_xs.action" method="post" id='form1'>

	<input type=hidden value='1' name='page.a'/>
   	所属客户
   	<select name='customer.cust_id' id='select'>
   		<option value='-1' >请选择</option>
   		<s:iterator value="lc" var="b">
   			<option value='<s:property value="#b.cust_id"/>'>
   				<s:property value="#b.cust_name"/>
   			</option>
   		</s:iterator>
   	</select><br>
   	联系人姓名<input type=text name='lkm_name' value='<s:property value="l1.lkm_name"/>'/><br>
   	<input type='submit' value='tijiao' />

 </form>
 
 
 
 <p id="p"> 		<s:iterator value="ll" var="c">
 		<s:property value="#c.lkm_id"/>--
 		<s:property value="#c.lkm_name"/>--
 		<span><s:property value="#c.lkm_gender"/></span>--
 		<s:property value="#c.lkm_phone"/>--
 		<s:property value="#c.lkm_mobile"/>--
 		<s:property value="#c.customer.cust_name"/>--

<a href='${pageContext.request.contextPath}/linkman_xgUI?lkm_id=<s:property value="#c.lkm_id"/>'>修改</a>	
<a class="delete" href='javascript:void(0)' what="<s:property value="#c.lkm_id"/>">删除</a>

 		<br>
 	</s:iterator></p>
 	<hr>
 	<tr>
 	<td>当前第<select id='se'></select>页</td>
 		<td><a href="javascript:void(0)" onclick="ff(${page.a}-1)">上一页</a></td>
 		<td><a href="javascript:void(0)" onclick="ff(${page.a}+1)">下一页</a></td>	
 		<td>共条${page.c}数据，${page.d}页</td>
 	</tr>
 
  

 
 <script type="text/javascript">
 	$(function(){
 		f={'0':'男','1':'女'};
 		sp=$("#p span");
		sp.each(function(){
			$(this).text(f[$(this).text()]);
		})
 		
 		h="";
 		for(i=1;i<${page.d}+1;i++){
 			if(i==${page.a}){
 				h+="<option value='"+i+"' selected=true>"+i+"</option>";
 			}else{
 				h+="<option value='"+i+"'>"+i+"</option>";
 		}}
 		$("#se").html(h);
 			
 		$(".delete").click(function(e){
 			a=confirm("yes or no");
 		 	 h='${pageContext.request.contextPath}/linkman_delete?lkm_id='+$(this).attr("what"); 
 			console.log(h,a);
 			if(a){
				location.href=h; 				
 			}
 		})
 		
 		v1='<s:property value="l1.customer.cust_id" />';
 		console.log("v1=",v1);
 		$("#select option[value="+v1+"]").attr("selected",true);
 		
 		$("#se").change(function(){
 			ff($(this).val()); 			
 		})

 	})
 	 	function ff(i){
			$("input[type=hidden]").val(i);
			$("#form1").submit();
		}
 	
 </script>	

  </body>
</html>
