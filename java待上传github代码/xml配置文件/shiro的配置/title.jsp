<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <!--这一行必须，否则shiro:hasPermission无效，下面项目全部展示 -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>				
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>

<body onSelectStart="return true">


<div class="headerBg">
 
	 <!-- 当jsp页面碰到shiro标签时就执行AuthRealm中授权方法 -->
    <shiro:hasPermission name="系统首页">
	   <span id="topmenu" onclick="toModule('home');">系统首页</span><span id="tm_separator"></span>
	</shiro:hasPermission>
	<shiro:hasPermission name="货运管理">
		<span id="topmenu" onclick="toModule('cargo');">货运管理</span><span id="tm_separator"></span>
	</shiro:hasPermission>
	<shiro:hasPermission name="统计分析">
	<span id="topmenu" onclick="toModule('stat');">统计分析</span><span id="tm_separator"></span>
	</shiro:hasPermission>
	<shiro:hasPermission name="基础信息">
	<span id="topmenu" onclick="toModule('baseinfo');">基础信息</span><span id="tm_separator"></span>
	</shiro:hasPermission>
	<shiro:hasPermission name="系统管理">
	<span id="topmenu" onclick="toModule('sysadmin');">系统管理</span>
	</shiro:hasPermission> 

</div>

</body>
</html>