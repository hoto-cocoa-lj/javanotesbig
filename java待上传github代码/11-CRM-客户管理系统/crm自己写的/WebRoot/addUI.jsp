<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
    <%@taglib prefix="s"  uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<s:property value="test1"/>
  <hr> 
   <form action="${pageContext.request.contextPath}/customer_save.action" method="post">

   	客户名称<input type=text name='cust_name' /><br>
   	固定电话<input type=text name='cust_phone' /><br>
   	移动电话<input type=text name='cust_mobile' /><br>
   	客户信息来源

   <select name='cust_source.dict_id' >	
   		<s:iterator value="l1" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select>
   	<br>

	客户所属行业
   <select name='cust_industry.dict_id' >	
   		<s:iterator value="l2" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select>
   	<br>
   	
   	客户<span style="opacity:0">级别</span>级别
   										
   <select name='cust_level.dict_id' >	
   		<s:iterator value="l3" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select><br> 

   	<input type='submit' value='tijiao' /><br>
   </form> <br>
   
  <s:debug></s:debug> 
      <hr> 
    
    
    
    
    
   <form action="${pageContext.request.contextPath}/customer_xs.action" method="post">
   <input type=text name='test1' /><br>
   	客户名称<input type=text name='cust_name' value='<s:property value="c1.cust_name"/>'/><br>
   	客户信息来源
   <select name='cust_source.dict_id'  id=s1>	
   		<s:iterator value="l1" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select>
   	<br>

	客户所属行业
   <select name='cust_industry.dict_id'  id=s2>	
   		<s:iterator value="l2" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select>
   	<br>
   	
   	客户<span style="opacity:0">级别</span>级别
   										
   <select name='cust_level.dict_id' id=s3>	
   		<s:iterator value="l3" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select><br> 

   	<input type='submit' value='tijiao' /><br>
   </form> <hr>
    	
<%@include file="list.jsp" %>

<script type="text/javascript">
	$(function(){

		var v1="<s:property value='c1.cust_source.dict_id'/>";
		var v2="<s:property value='c1.cust_industry.dict_id'/>";
		var v3="<s:property value='c1.cust_level.dict_id'/>";
		
		
/* 		if(v1==""){
			v1="<s:property value='c1.cust_source.dict_id'/>";
		}
		if(v2==""){
			v2="<s:property value='c1.cust_industry.dict_id'/>";
		}
		if(v3==""){
			v3="<s:property value='c1.cust_level.dict_id'/>";
		}  */
		
		
		$("#s1>option[value="+v1+"]").attr("selected","selected");
		$("#s2>option[value="+v2+"]").attr("selected","selected");
		$("#s3>option[value="+v3+"]").attr("selected","selected");

		v4=<%=request.getServletContext().getAttribute("c")%>
/* 		var v4=${c.cust_source.dict_id};
		var v5=${c.cust_industry.dict_id};
		var v6=${c.cust_level.dict_id}; */
		
		console.log("v123=",v1,v2,v3);
		console.log("v456=",v4,5,6);
	})
</script>

</body>
</html>