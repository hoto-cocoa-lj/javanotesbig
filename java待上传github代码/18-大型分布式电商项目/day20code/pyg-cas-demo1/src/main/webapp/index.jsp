<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>欢迎您:<%=request.getRemoteUser() %>,登录系统</h1>
<a href="http://192.168.66.66:9100/cas/logout?service=http://www.baidu.com">推出登录</a>
</body>
</html>