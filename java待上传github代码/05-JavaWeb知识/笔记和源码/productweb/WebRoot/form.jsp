<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>

	</head> 
<body>
<h1>h1hg1h1</h1>
	<form  action='${pageContext.request.contextPath}/login' method="post">
	<input type=text class=a1 name=ud />账号${z}<br>
	<input type="password" class=a2 name=pd />密码<br>
	<input type="checkbox" class=a2 name=re value=true />
	<input type=submit class=a7 value=a7 /><br>
	</form>	

<form  action='${pageContext.request.contextPath}/login' method="post">
	<input type=text class=a1 name=username />账号<br>
	<input type="password" class=a2 name=password />密码<br>
<input type=text class=a3 name=email  />邮箱<br>
<input type=text class=a3 name=name  />名字<br>	

	<input type="radio" class=a3 name=sex value=>男 />男
	<input type="radio" class=a3 name=sex value=女 />女<br>
<input type="date" class=a3 name=d />日期<br>

	<input type=submit class=a7 value=a7 /><br>
</form>
	</body>
</html>
