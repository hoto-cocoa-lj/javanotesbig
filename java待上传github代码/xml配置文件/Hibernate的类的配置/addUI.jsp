<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
%>
<%@taglib prefix="s"  uri="/struts-tags"%> 
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <form action="user_save.action" method="post">
   	客户名称<input type=text name='cust_name' /><br>
   	固定电话<input type=text name='cust_phone' /><br>
   	移动电话<input type=text name='cust_mobile' /><br><s:debug></s:debug><hr>
   	客户信息来源

   <select name='cust_source' >	
   		<s:iterator value="l1" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select>
   	<br>

   	客户所属行业
   <select name='cust_industry' >	
   		<s:iterator value="l2" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select>
   	<br>
   	客户<span style="opacity:0">级别</span>级别
   <select name='cust_level' >	
   		<s:iterator value="l3" var="b">
   			<option value='<s:property value="#b.dict_id"/>'>
				<s:property value="#b.dict_item_name"/>
			</option>
   		</s:iterator>   		
   	</select><br>
   	<input type=submit value=tijiao /><br>
   </form> <br>
  </body>
</html>
