<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<style type="text/css">
.s {
	color: red;
	border: 1px yellow solid;
	background-color: rgb(222, 222, 222);
	padding: 2px 10px 2px 5px;
}
</style>
<script type="text/javascript" src="<%=path%>/resource/jquery3.3.1.js"></script>
<script type="text/javascript">
	$(function() {
		var x = [ '', '', '' ];
		var div = $('#div');


		$('#s1').click(function(e) {
			text = $(e.target).text();
			name = $(e.target).attr('name');
			x[name] = text;
			read();
		})
		
		$('#div').click(function(e) {
			name = $(e.target).attr('name');
			x[name] = '';
			read();
		})

		function read() {
			s = '';
			for (i = 0; i < x.length; i++) {			
				if (x[i] != '') {
					s = s + "<span class='s' name='"+i+"'>"+x[i]+"     x"+"</span>";
				}
			}
			div.html(s);
		}
	})


</script>
</head>

<body>
	<div id='div'></div>
	This is my JSP page.
	<br>
	<div id='s1'>
		<span class='s' name='0'>a1</span><span class='s' name='0'>a2</span><br>
		<span class='s' name='1'>b1</span><span class='s' name='1'>b2</span><br>
		<span class='s' name='2'>c1</span><span class='s' name='2'>c2</span>
	</div>
	<br>
</body>
</html>
