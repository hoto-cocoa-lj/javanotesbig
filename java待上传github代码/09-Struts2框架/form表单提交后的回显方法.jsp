<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
    <%@taglib prefix="s"  uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<title>Insert title here</title>
</head>
<body>

 <s:debug></s:debug>    <hr> 


   <form action="${pageContext.request.contextPath}/customer_xs.action" method="post">

   
   要点1：
   客户信息这行代码里，action里的Customer对象c用name属性获取值（value），然后该c一直在值栈里，
   所以ognl表达式<s:property value="cust_name"/>赋值value即可回显。
   
   
   	客户名称<input type=text name='cust_name' value='<s:property value="cust_name"/>'/><br>
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



要点2：
select下拉框的回显方法如下，可见js代码里可以直接混和使用ognl表达式。


<script type="text/javascript">
	$(function(){

		var v1="<s:property value='cust_source.dict_id'/>";
		$("#s1>option[value="+v1+"]").attr("selected","selected");
		var v2="<s:property value='cust_industry.dict_id'/>";
		$("#s2>option[value="+v2+"]").attr("selected","selected");
		var v3="<s:property value='cust_level.dict_id'/>";
		$("#s3>option[value="+v3+"]").attr("selected","selected");
		console.log(v1,v2,v3);
	})
</script>

</body>
</html>