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
 	<s:iterator value="customerList" var="c">
 		<s:property value="#c.cust_id"/>--
 		<s:property value="#c.cust_name"/>--
 		<s:property value="#c.cust_phone"/>--
 		<s:property value="#c.cust_mobile"/>--
 		<s:property value="#c.cust_source.dict_item_name"/>--
<s:property value="#c.cust_industry.dict_item_name"/>--
<s:property value="#c.cust_level.dict_item_name"/>-- 	
<a href='${pageContext.request.contextPath}/xgUI?cust_id=<s:property value="#c.cust_id"/>'>修改</a>	
<a class="delete" href='javascript:void(0)' what="<s:property value="#c.cust_id"/>">删除</a>

 		<br>
 	</s:iterator>
 
 <script type="text/javascript">
 	$(function(){
 		$(".delete").click(function(e){
 			a=confirm("yes or no");
 		 	 h='${pageContext.request.contextPath}/delete?cust_id='+$(this).attr("what"); 
 			console.log(h,a);
 			if(a){
				location.href=h; 				
 			}
 		})
 	})
 </script>	

  </body>
</html>
