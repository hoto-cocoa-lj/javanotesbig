<%@page import="java.util.Iterator" import="java.util.HashMap" import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList" import="bean.User"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method=post action="${pageContext.request.contextPath}/s1">
<input type=text name=sql>
<input type=submit />
</form>
<a href="${pageContext.request.contextPath}/s1?x=1&b=2&c=3&c=4" >go</a>

<%int i=2;request.setAttribute("i", i); %>
<c:if test="${i>1}" var="t">nishiishabi${t}</c:if><c:if test="${i<4}" var="t">asd${t}</c:if><hr><hr>

<%int[] a=new int[]{3,4,5,6}; request.setAttribute("a",a);%>
<c:forEach begin="1" end="8" step="1" var="z">${z}</c:forEach>
<c:forEach items="${a }" var="z">${z}</c:forEach>

<%Map m=new  HashMap(); m.put("x",1); m.put("y",2); m.put("z",3);request.setAttribute("m",m);%>

<c:forEach items="${m}" var="z">${z.key}=${z.value}</c:forEach>

</body>
</html>