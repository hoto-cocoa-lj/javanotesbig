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
   <form action="${pageContext.request.contextPath}/linkman_save.action" method="post">

   	所属客户<select name='customer.cust_id' >
   	<option value='-1' >请选择
   		<s:iterator value="lc" var="b">
   			<option value='<s:property value="#b.cust_id"/>'>
   				<s:property value="#b.cust_name"/>
   			</option>
   		</s:iterator>
   	</select><br>
   	联系人姓名<input type=text name='lkm_name' /><br>
   	联系人性别<input type=radio name='lkm_gender' value='0' />男
   				<input type=radio	 name='lkm_gender' value='1' />女<br>
   	联系人电话<input type=text name='lkm_phone' /><br>
   	联系人手机<input type=text name='lkm_mobile' /><br>
   	<input type=submit value=tijiao />

 </form>

</body>
</html>