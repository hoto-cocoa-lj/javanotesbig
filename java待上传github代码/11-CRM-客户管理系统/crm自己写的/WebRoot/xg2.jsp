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
    <%-- <s:debug></s:debug> --%>
<a href="javascript:void(0)" id="aaa">aaa</a>
   <form action="${pageContext.request.contextPath}/linkman_xg.action" method="post">
<input type=hidden name='lkm_id' value='<s:property value="l1.lkm_id"/>'/><br>
 	所属客户<select name='customer.cust_id' id='s1'>
   	<option value='-1' >请选择
   		<s:iterator value="lc" var="b">
   			<option value='<s:property value="#b.cust_id"/>'>
   				<s:property value="#b.cust_name"/>
   			</option>
   		</s:iterator>
   	</select><br>
   	联系人姓名<input type=text name='lkm_name' value='<s:property value="l1.lkm_name"/>'/><br>
   	联系人性别<input type=radio name='lkm_gender' value='0' />男
   				<input type=radio	 name='lkm_gender' value='1' />女<br>
   	联系人电话<input type=text name='lkm_phone' value='<s:property value="l1.lkm_phone"/>'/><br>
   	联系人手机<input type=text name='lkm_mobile' value='<s:property value="l1.lkm_mobile"/>'/><br>
   	<input type=submit value=tijiao />
   </form> <hr>
    	
<%@include file="list.jsp" %>

<script type="text/javascript">
	$(function(){

		var v1="<s:property value='l1.customer.cust_id'/>";	
		$("#s1>option[value="+v1+"]").attr("selected",true);

	var v2="<s:property value='l1.lkm_gender'/>";	
		$("input[type=radio][value="+v2+"]").attr("checked",true);

		$("#aaa").click(function(){
			h="${pageContext.request.contextPath}/customer_xs.action?cust_name=&"+
				"cust_source.dict_id="+$("#s1").val()+
				"&cust_industry.dict_id="+$("#s2").val()+
				"&cust_level.dict_id="+$("#s3").val();
				alert(h);
			location.href=h;
		})
	})
</script>

</body>
</html>