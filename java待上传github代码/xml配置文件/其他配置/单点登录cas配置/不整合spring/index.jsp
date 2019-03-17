<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="org.jasig.cas.client.util.AssertionHolder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>品优购</title>
</head>
<body>
欢迎来到品优购1111111111
<%=request.getRemoteUser()%>
---
<%=AssertionHolder.getAssertion().getPrincipal().getName()%>
</body>

<a href="http://localhost:9100/cas/logout?service=http://localhost:9999">tomcat7:run</a>
</html>